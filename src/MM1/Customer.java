package MM1;

import java.util.Random;

public class Customer {
	public double interval_time;//距上一位顾客到达时间间隔
	public double serve_time;//服务时间
	public double arrival_time;//到达的真实时间
	public int id;//顾客编号
	
	//根据到达率和服务率，随机生成指数分布的到达时间间隔和服务时间
	public Customer(double v_arrival,double v_serve,int _id) {
		Random rand=new Random();
		interval_time=-1.0*Math.log(1-rand.nextDouble())/v_arrival;
		serve_time=-1.0*Math.log(1-rand.nextDouble())/v_serve;
		arrival_time=0;
		id=_id;
	}
	
}
