(ns the3rs.handler
  (:require [compojure.core :refer :all]
            [ring.middleware.anti-forgery :as forgery]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.util.response :refer [response]]))

(def customer-store (atom {1 {:id 1 :name "Chris"}}))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/customers/:id" [id]
       (response {:customer (@customer-store (read-string id))}))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))



(comment
  (app (ring.mock.request/request :get "/"))
  (app (ring.mock.request/request :get "/customers/1"))
  )
