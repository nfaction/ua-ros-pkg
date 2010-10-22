/* auto-generated by genmsg_cpp from /home/dhewlett/ros/ua-ros-pkg/ua_experimental/oomdp_msgs/msg/MDPClassDescription.msg.  Do not edit! */
package ros.pkg.oomdp_msgs.msg;


import java.nio.ByteBuffer;

public  class MDPClassDescription extends ros.communication.Message
{

  public java.lang.String name;
  public java.lang.String[] attributes;

  public MDPClassDescription() {
 super();
    name = new java.lang.String();
  attributes = new java.lang.String[0];

  }
  public static java.lang.String __s_getDataType() { return "oomdp_msgs/MDPClassDescription"; }
  public static java.lang.String __s_getMD5Sum() { return "7f57b075eab7259604a9de68c09f2b6d"; }
  public static java.lang.String __s_getMessageDefinition()
  {
    return 
    "string name\n" + 
    "string[] attributes\n" + 
    "\n" + 
    "\n" + 
    "";
  }
  public java.lang.String getDataType() { return __s_getDataType(); }
  public java.lang.String getMD5Sum()   { return __s_getMD5Sum(); }
  public java.lang.String getMessageDefinition() { return __s_getMessageDefinition(); }
  public MDPClassDescription clone() {
    MDPClassDescription clone = (MDPClassDescription)super.clone();
      attributes =  (java.lang.String[])(clone.attributes.clone());
    return clone;
  }

  public static java.util.Map<java.lang.String, java.lang.String> fieldTypes() {
         java.util.HashMap<java.lang.String, java.lang.String> m = new java.util.HashMap<java.lang.String, java.lang.String>  ();      m.put("name", "java.lang.String");
     m.put("attributes", "java.lang.String[]");
     return m;
  }

  public static java.util.Set<java.lang.String> submessageTypes() {
         java.util.HashSet<java.lang.String> s = new java.util.HashSet<java.lang.String>  ();      return s;
  }

  public void setTo(ros.communication.Message __m) {
    if (!(__m instanceof MDPClassDescription)) throw new RuntimeException("Invalid Type");
    MDPClassDescription __m2 = (MDPClassDescription) __m;
    name = __m2.name;
    attributes = __m2.attributes;
    }

  int calc_attributes_array_serialization_len() {
    int l = 0;
    for (int i = 0; i < attributes.length; i++) 
      l += 4 + attributes[i].length();
    return l;
  }
  public int serializationLength() 
  {
    int __l = 0;
    __l += 4 + name.length(); // name
    __l += 4 + calc_attributes_array_serialization_len(); // attributes
    return __l;
  }
  public void serialize(ByteBuffer bb, int seq) {
    Serialization.writeString(bb, name);
    bb.putInt(attributes.length);
    for (java.lang.String x : attributes)
      Serialization.writeString(bb, x);
  }
  public void deserialize(ByteBuffer bb)  {
    name = Serialization.readString(bb);
     int attributes_len = bb.getInt();
    attributes = new java.lang.String[attributes_len];
    for(int i = 0; i < attributes_len; i++)
      attributes[i] = Serialization.readString(bb);
  }
}

