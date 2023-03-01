(ns prj2-sol.core)

(defrecord OrderItem
  [ sku        ;keyword ID identifying item
    category   ;keyword giving item category
    n-units    ;# of units of item
    unit-price ;price per unit of item
  ])

;; #1: 15-points
;;given a list items of OrderItem, return the total for
;;the order, i.e. sum n-units*unit-price over all items
;;must be recursive but cannot use loop or recur
(defn items-total1 [items]
  "return total price of all items"
  :TODO)

;; #2: 15-points
;;given a list items of OrderItem and a category,
;;return list of elements of items having specified category.
;;must be implemented using recursion
(defn items-with-category1 [items category]
  "return sublist of items having specified category"
  :TODO)

;; #3: 15-points
;;same specs as items-total1 but must be implemented using
;;loop and recur
(defn items-total2 [items]
  "return total price of all items"
  :TODO)

;; #4: 10-points
;;given a list items of OrderItem return a list of all the :sku's
;;cannot use explicit recursion
(defn item-skus [items]
  "return :sku's of all items"
  :TODO)

;; #5: 10-points
;;given a list items of OrderItem, return a list of skus of those elements
;;in items having unit-price greater than price
;;cannot use explicit recursion
(defn expensive-item-skus [items price]
  "return list of :sku's of all items having :unit-price > price"
  :TODO)

;; #6: 10-points
;;same specs as items-total1 but cannot use explicit recursion
(defn items-total3 [items]
  "return total price of all items"  
  :TODO)
  

;; #7: 10-points
;;same spec as items-with-category1, but cannot use explicit recursion
(defn items-with-category2 [items category]
  "return sublist of items having specified category"
  :TODO)

;; #8: 15-points 
;;given a list items of OrderItem and an optional category
;;return total of n-units of category in items.  If the
;;optional category is not specified, return total of n-units
;;of all items.
;;cannot use explicit recursion
(defn item-category-n-units 
  "return sum of :n-units of items for category (all categories if unspecified)"
  ([items] :TODO)
  ([items category] :TODO))

