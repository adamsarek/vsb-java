package pl1.utils;
import java.util.ArrayList;

public class Stack<T> 
{
	private ArrayList<T> stack;
	
	public Stack()
	{
		stack = new ArrayList<T>();
	}
	
	public void push(T t)
	{
		stack.add(t);
	}
	
	public T pop()
	{
		if(size() > 0)
		{
			T ret = stack.get(size() - 1);
			stack.remove(size() - 1);
			return ret;
			
		}
		else
		{
			System.out.println("Function pop could not be executed, because the stack is empty.");
			return null;
		}
	}
	
	public int size()
	{
		return stack.size();
	}
}
