package mina.marszhang.minatcp02.common;

import java.util.Observable;
/**
 * <B style="color:#00f"> </B>
 * <br>
 * @author zhanglin
 */
public class CommonObservable extends Observable {

	public void trigger() {
		setChanged();
		notifyObservers(); 
	}
}
