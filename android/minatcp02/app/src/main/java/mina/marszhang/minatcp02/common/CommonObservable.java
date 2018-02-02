package mina.marszhang.minatcp02.common;

import java.util.Observable;
/**
 * <B style="color:#00f"> 观察者</B>
 * <br>
 * @author zhanglin
 */
public class CommonObservable extends Observable {

	public void trigger() {
		setChanged();
		notifyObservers(); 
	}
}
