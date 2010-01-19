/* auto-generated by gensrv_java from /home/dhewlett/ros/ua-ros-pkg/wubble/wubble_environments/srv/IcarusWorldState.srv.  Do not edit! */
package ros.pkg.wubble_environments.srv;

import java.nio.ByteBuffer;

public class IcarusWorldState extends ros.communication.Service<IcarusWorldState.Request, IcarusWorldState.Response> 
{

public static String __s_getDataType() { return "wubble_environments/IcarusWorldState"; }
public static String __s_getMD5Sum() { return "af6d3a99f0fbeb66d3248fa4b3e675fb"; }

public String getDataType() { return IcarusWorldState.__s_getDataType(); }
public String getMD5Sum() { return IcarusWorldState.__s_getMD5Sum(); }

public static class Request extends ros.communication.Message
{


  public Request() {
 super();

  }
  public static java.lang.String __s_getDataType() { return "wubble_environments/IcarusWorldStateRequest"; }
  public static java.lang.String __s_getMD5Sum() { return ""; }
  public static java.lang.String __s_getMessageDefinition()
  {
    return 
    "\n" + 
    "";
  }
  public java.lang.String getDataType() { return __s_getDataType(); }
  public java.lang.String getMD5Sum()   { return __s_getMD5Sum(); }
  public java.lang.String getMessageDefinition() { return __s_getMessageDefinition(); }
  public static java.lang.String __s_getServerMD5Sum() { return ("af6d3a99f0fbeb66d3248fa4b3e675fb"); }
  public java.lang.String getServerMD5Sum() { return __s_getServerMD5Sum(); }
  public static java.lang.String  __s_getServiceDataType() { return ("wubble_environments/IcarusWorldState"); }
  public java.lang.String getServiceDataType() { return __s_getServiceDataType(); }
  public Request clone() {
    Request clone = (Request)super.clone();
    return clone;
  }

  public static java.util.Map<java.lang.String, java.lang.String> fieldTypes() {
         java.util.HashMap<java.lang.String, java.lang.String> m = new java.util.HashMap<java.lang.String, java.lang.String>  ();      return m;
  }

  public static java.util.Set<java.lang.String> submessageTypes() {
         java.util.HashSet<java.lang.String> s = new java.util.HashSet<java.lang.String>  ();      return s;
  }

  public void setTo(ros.communication.Message __m) {
    if (!(__m instanceof Request)) throw new RuntimeException("Invalid Type");
    Request __m2 = (Request) __m;
    }

  public int serializationLength() 
  {
    int __l = 0;
    return __l;
  }
  public void serialize(ByteBuffer bb, int seq) {
  }
  public void deserialize(ByteBuffer bb)  {
  }
}

public static class Response extends ros.communication.Message
{

  public java.lang.String state;

  public Response() {
 super();
    state = new java.lang.String();

  }
  public static java.lang.String __s_getDataType() { return "wubble_environments/IcarusWorldStateResponse"; }
  public static java.lang.String __s_getMD5Sum() { return ""; }
  public static java.lang.String __s_getMessageDefinition()
  {
    return 
    "string state\n" + 
    "\n" + 
    "\n" + 
    "";
  }
  public java.lang.String getDataType() { return __s_getDataType(); }
  public java.lang.String getMD5Sum()   { return __s_getMD5Sum(); }
  public java.lang.String getMessageDefinition() { return __s_getMessageDefinition(); }
  public static java.lang.String __s_getServerMD5Sum() { return ("af6d3a99f0fbeb66d3248fa4b3e675fb"); }
  public java.lang.String getServerMD5Sum() { return __s_getServerMD5Sum(); }
  public static java.lang.String  __s_getServiceDataType() { return ("wubble_environments/IcarusWorldState"); }
  public java.lang.String getServiceDataType() { return __s_getServiceDataType(); }
  public Response clone() {
    Response clone = (Response)super.clone();
    return clone;
  }

  public static java.util.Map<java.lang.String, java.lang.String> fieldTypes() {
         java.util.HashMap<java.lang.String, java.lang.String> m = new java.util.HashMap<java.lang.String, java.lang.String>  ();      m.put("state", "java.lang.String");
     return m;
  }

  public static java.util.Set<java.lang.String> submessageTypes() {
         java.util.HashSet<java.lang.String> s = new java.util.HashSet<java.lang.String>  ();      return s;
  }

  public void setTo(ros.communication.Message __m) {
    if (!(__m instanceof Response)) throw new RuntimeException("Invalid Type");
    Response __m2 = (Response) __m;
    state = __m2.state;
    }

  public int serializationLength() 
  {
    int __l = 0;
    __l += 4 + state.length(); // state
    return __l;
  }
  public void serialize(ByteBuffer bb, int seq) {
    Serialization.writeString(bb, state);
  }
  public void deserialize(ByteBuffer bb)  {
    state = Serialization.readString(bb);
  }
}

public IcarusWorldState.Request createRequest() { return new IcarusWorldState.Request(); }public IcarusWorldState.Response createResponse() { return new IcarusWorldState.Response(); }}

