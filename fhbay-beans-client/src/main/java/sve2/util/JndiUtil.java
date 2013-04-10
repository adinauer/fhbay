package sve2.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JndiUtil {
  private static Context context;

  public static Context getInitialContext() throws NamingException {
    if (context == null) context = new InitialContext();
    return context;
  }

  @SuppressWarnings("unchecked")
  public static <T> T getRemoteObject(String jndiName, Class<T> intfClass) throws NamingException {
    Object ref = getInitialContext().lookup(jndiName);
    // return (T) PortableRemoteObject.narrow(ref, intfClass);
    return (T) ref;
  }
}
