package katalon.fw.lib

public class Window {
	private static final Window instance = new Window();
	private Map<Class,Object> map = new HashMap<Class,Object>();

	/**
	 * 
	 * @param <T> specific window
	 * @param classOf
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T> T open(Class<T> classOf) throws InstantiationException, IllegalAccessException{
		synchronized(instance) {
			if(!instance.map.containsKey(classOf)) {
				T obj = classOf.newInstance();
				instance.map.put(classOf,obj);
			}
			return (T)instance.map.get(classOf);
		}
	}
}
