(in-package :simulation_semantics)

(defun get-object-path ()
  (let ((proc (sb-ext:run-program "rospack" '("find" "simulator_experiments") 
                                  :wait t :output :stream :search t)))
    (concatenate 'string
                 (read-line (sb-ext:process-output proc))
                 "/objects/")))

;; Seems like we do actually need this?
(parse-xml-file 
 (concatenate 'string (get-object-path) "blue_box.xml"))

(parse-xml-file 
 (concatenate 'string (get-object-path) "blue_sphere.xml"))

(defparameter *robot-xml* 
  (parse-xml-file 
   (concatenate 'string (get-object-path) "robot.xml")))
