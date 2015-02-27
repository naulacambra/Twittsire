package managers;

public class StringManager {
	public static String capitalize(String line) {
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
	
	public static String removeFirst(String line){
		return line.substring(1, line.length());
	}
	
	public static String removeLast(String line){
		return line.substring(0, line.length()-1);
	}
	
	public static String removeFirstAndLast(String line){
		return StringManager.removeFirst(StringManager.removeLast(line));
	}
}
