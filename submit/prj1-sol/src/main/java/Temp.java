import org.json.*;

import java.util.Arrays;
import java.util.regex.Pattern;
/**
 * parser
 */
public class Temp {

    Token tok = null;
    int index = 0;
    static String[] inputList = null;
    String[] reservedList = {"var", "end"};
    StringBuilder response = new StringBuilder("[");
    Boolean validInput = true;

    public static void main(String[] args) {
        
        //String input = "#simple single declaration\nvar variable:number;\nvar _some_2OtherVar23 : string;";
        //String input = "# this file contains a single declaration using a non-nested record\nvar point3D : \nrecord\nx : number ;\ny : number ;\nz : number ;\nend ;";
        //String input = "var personPosition : record\n  name : record\n    firstName : string ;\n    lastName : string ;\n  end ;\n  position : record\n    x : number ;\n    y : number ;\n    z : number ;\n  end ;\nend ;";
        String input = "# test for a declaration with heavy nesting\n\nvar super_nested : record\n  a_depth3 : record\n    a_depth2 : record\n      a_depth1 : record\n        a_depth0 : record\n          n : number ;\n          str : string ;\n\tend ; #a_depth0\n        b_depth0 : record\n\t  str : string ;\n\t  n : number ;\n\tend ; #b_depth0\n      end ; #a_depth1\n      b_depth1 : record\n        a_depth0 : record\n          nx : number ;\n\t  strx : string ;\n\tend ;\n        b_depth0 : record\n\t  strx : string ;\n\t  nx : number ;\n\tend ;\n      end ; #b_depth1\n    end ; #a_depth2\n    x_depth2 : record\n      a_depth1 : record\n        a_depth0 : record\n\t  n : number ;\n\t  str : string ; \n\tend ;\n        b_depth0 : record\n\t  str : string ;\n\t  n : number ;\n\tend ;\n      end ; #a_depth1\n      b_depth1 : record\n        a_depth0 : record\n\t  nx : number ;\n\t  strx : string ;\n\tend ;\n        b_depth0 : record\n\t  strx : string ;\n\t  nx : number ;\n\tend ;\n      end ; #b_depth1\n    end ; #a_depth2\n  end ; #a_depth3\n  z_depth3 : record\n    x_depth2 : record\n      y_depth1 : record\n        u_depth0 : record\n          n : number ;\n          str : string ;\n\tend ;\n        v_depth0 : record\n\t  str : string ;\n\t  n : number ;\n\tend ;\n      end ; #y_depth1\n      g_depth1 : record\n        a_depth0 : record\n          nx : number ;\n\t  strx : string ;\n\tend ;\n        b_depth0 : record\n\t  strx : string ;\n\t  nx : number ;\n\tend ;\n      end ; #g_depth1\n    end ; #x_depth2\n    y_depth2 : record\n      a_depth1 : record\n        a_depth0 : record\n\t  n : number ;\n\t  str : string ;\n\tend ;\n        b_depth0 : record\n\t  str : string ;\n\t  n : number ;\n\tend ;\n      end ; #a_depth1\n      b_depth1 : record\n        a_depth0 : record\n\t  nx : number ;\n\t  strx : string ;\n\tend ;\n        b_depth0 : record\n\t  strx : string ;\n\t  nx : number ;\n\tend ;\n      end ; #b_depth1\n    end ; #y_depth2\n  end ; #z_depth3\nend ; #super_nested";

        //String input = "var single22:number ;\n\nvar\nsingle33\n:\nstring #some comment\n#another comment\n  ;\n\nvar nested_23 : record\n  triangle : record  #3 points\n    point1 : record x : number ; y : number ; end ;\n    point2 : record x : number ; y : number ; end ;\n    point3 : record x : number ; y : number ; end ;\n  end ;\n  color : record #RGB components\n    r :  number ; g : number ; b : number ; \n  end ;\n  index : number ;\n  pos : record x : number ; y : number ; end ;\nend ;";
        //String input = "var x : string"; //issue
        //String input = "x : string ;";
        //String input = "var x number;";
        //String input = "var number x;";
        //String input = "var x: record a number end;";
        //String input = "var Record: record x: record end; end;"; //issue
        //String input = "var x : string1;";
        //String input = "var rec : record end;";
        //String input = "var r : record x : number end;";
        //String input = "var x: record a number end;";
        //String input = "var z : record x: string1; end;";
        //String input = "var Record: record x: record end; end;";
        //String input = "var a: number; .";
        //String input = "# this file contains no declarations";
        
        
        input = input.replaceAll("#.*", " ");
        
        //Cleaning String
        input = input.replaceAll(":", " : ");
        input = input.replaceAll(";", " ; ");
        input = input.trim();
        
        inputList = input.split("\\s+");
    
        Parser parser = new Parser();
        parser.parseInput();
    }

    public void parseInput(){
        
        tok = new Token();
        /*while(inputList[index].isBlank()){
            index++;
        }*/
        while(index < inputList.length - 1){
            tok.setLexeme(inputList[index]);
            declaration();
        }
        
        response.append("]");

        //System.out.println(response.toString());
        if(validInput){
            try{
                System.out.println(new JSONArray(response.toString()));
            }catch(Exception ex){
                System.out.println("Invalid token : "+tok.getLexeme());
            }
            
        }
        
        //return jsonString;
    }

    public void declaration(){

        if(validInput){
            reserved();
            identifier();
            field();
        }
        

    }

    public void reserved(){

        if(peek("var")){
            tok.setKind("reserved word");
            consume("var");
        }else{ 
            tok.setKind("reserved word");
            consume("end");
        }

    }

    public void identifier(){
        if(!Arrays.asList(reservedList).contains(tok.getLexeme()) && Pattern.compile("^[a-zA-Z0-9_]*$").matcher(tok.getLexeme()).find() && !tok.getLexeme().isBlank()){
            tok.setKind("Identifier");
            consume(tok.getLexeme());
            tok.setKind(":");
            consume(":");
            type();
        }else{
            //consume("");
            //field();
            if(validInput && Arrays.asList(reservedList).contains(tok.getLexeme())){
                System.out.println("Expecting identifier");
                validInput = false;
            }
            
        }
    }

    public void field(){

        if(peek(":")){
            tok.setKind(":");
            consume(":");
            type();
            field();
        }else if(peek(";")){
            tok.setKind(";");
            consume(";");
            
            /*if(index < inputList.length - 1){
                if(!Arrays.asList(reservedList).contains(tok.getLexeme()) && Pattern.compile("^[a-zA-Z0-9_]*$").matcher(tok.getLexeme()).find()){
                    tok.setKind("Identifier");
                    consume(tok.getLexeme());
                    field();
                }else{
                    declaration();
                }
            }*/
            if(!tok.getLexeme().isBlank()){
                field();
            }
            //field();
        }else if(!Arrays.asList(reservedList).contains(tok.getLexeme()) && Pattern.compile("^[a-zA-Z0-9_]*$").matcher(tok.getLexeme()).find() && !tok.getLexeme().isBlank()){
            tok.setKind("Identifier");
            consume(tok.getLexeme());
            field();
        }else if(tok.getLexeme().isBlank()){
            System.out.println("Token is Missing");
            validInput = false;
            return;
        }
        else{
            declaration();
        }

    }

    public void type(){
        if(peek("number")){
            tok.setKind("type");
            consume("number");
            /*tok.setKind(";");
            consume(";");*/
        }else if(peek("string")){
            tok.setKind("type");
            consume("string");
            /*tok.setKind(";");
            consume(";");*/
        }else{
            tok.setKind("type");
            consume("record");
            identifier();
            //field();
        }

    }

    public boolean peek(String input){
        if(tok.getLexeme().equals(input)){
            return true;
        }
        return false;
    }

    public void consume(String input){

        if(input.equals("") && validInput){
            /*index++;
            tok.setLexeme(inputList[index]);*/
            System.out.println("Expecting "+tok.getKind()+" but got "+input);
            validInput = false;
            return;
        }
        if(tok.getLexeme().equals(input)){
            
            if(tok.getKind().equals("Identifier")){
                response.append("[\"").append(tok.getLexeme()).append("\",");
            }

            if(tok.getKind().equals("type")){
                if(tok.getLexeme().equals("record")){
                    response.append("[");
                }else{
                    response.append("\"").append(tok.getLexeme()).append("\"").append("],");
                }
                
            }

            if(tok.getLexeme().equals("end")){
                response.append("]],");
            }

            if(index < inputList.length - 1){
                index++;
                tok.setLexeme(inputList[index]);
            }else{
                tok.setLexeme("");
            }
            
        }else{
            if(validInput){
                System.out.println("Expecting "+tok.getKind()+" but got "+tok.getLexeme());
                validInput = false;
            }
            
        }
    }


}