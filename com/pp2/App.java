package com.pp2;

//Creacion de locking
//permite tener control sobre los hilos
public class App {
	
	//Creacion de variables estaticas
	private static int count1 = 0;
	private static int count2 = 0;
	
	//synchronized sincroniza los hilos
	//Creacion del metodo add()
	public synchronized static void add() {
		count1++;
	}
	
	//synchronized sincroniza los hilos
	//Creacion del metodo addAgain()
	public synchronized static void addAgain() {
		count2++;
	}

	//Creacion del metodo compute()
	public static void compute() {
		for(int i=0;i<100;++i) {
			/*Invocacion del metodo
			 * add() y addAgain()
			 */
			add();
			addAgain();
		}
	}
	
	public static void main(String[] args) {
		
		//Creacion del objeto t1
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				//Invocacion del metodo compute()
				compute();
			}
		});
		
		//Creacion del objeto t2
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				//Invocacion del metodo compute()
				compute();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			//join() une los hilos
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Impresion en pantalla de los resultados
		System.out.println("Count1="+count1+" - Count2="+count2);
	}
}

