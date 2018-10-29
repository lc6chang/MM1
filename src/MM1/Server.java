package MM1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Server {
	public double current_time;//当前时间
	public double serve_time;//服务器服务总时间
	
	public Server() {//初始化属性
		current_time=0.0;
		serve_time=0.0;
	}
	
	//服务器开始运行
	public void Run(ArrayList<Customer> que_init,Queue que_wait) {
		//打开日志文件
		FileWriter log=null;
		try {
			log = new FileWriter("./log.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//开始仿真
		while(que_init.size()!=0||que_wait.size()!=0) {
			//当等待队列没有顾客时，从生成顾客队列队首取出一名顾客，加入等待队列
			if(que_wait.size()==0) {
				Customer cus1=que_init.remove(0);
				cus1.arrival_time=que_wait.last_arrival_time+cus1.interval_time;//设置该顾客到达真实时间
				que_wait.add(cus1);
				que_wait.last_arrival_time=cus1.arrival_time;//更新上一个顾客到达时间
				current_time=cus1.arrival_time;//仿真时间推进
				//记录日志
				try {
					log.write(String.format("%.5f",current_time)+" 第"+cus1.id+"号顾客到达\r\n");
					log.flush();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			//开始服务等待队列队首的顾客
			double end_time=current_time+que_wait.get(0).serve_time;//计算服务结束时间
			que_wait.total_of_delays+=current_time-que_wait.get(0).arrival_time;//将该顾客等待时间加至总延迟时间
			serve_time+=que_wait.get(0).serve_time;//将该顾客服务时间加至服务器服务总时间
			Customer cus2=que_wait.remove(0);
			//记录日志
			try {
				log.write(String.format("%.5f",current_time)+" 第"+cus2.id+"号顾客开始服务\r\n");
				log.flush();
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			//将服务结束时间之前到达的顾客都加入到等待队列(不超过最大队列长度)
			while(que_init.size()!=0&&que_wait.size()<=que_wait.max_len) {
				if(que_init.get(0).interval_time+que_wait.last_arrival_time>=end_time) {
					break;
				}
				Customer cus3=que_init.remove(0);
				cus3.arrival_time=que_wait.last_arrival_time+cus3.interval_time;//设置该顾客到达真实时间
				que_wait.last_arrival_time=cus3.arrival_time;//更新上一个顾客到达时间
				current_time=cus3.arrival_time;//仿真时间推进至顾客到达时间
				que_wait.add(cus3);
				que_wait.num_cus_delayed++;//被延迟顾客总数+1
				//记录日志
				try {
					log.write(String.format("%.5f",current_time)+" 第"+cus3.id+"号顾客到达\r\n");
					log.write(String.format("%.5f",current_time)+" 第"+cus3.id+"号顾客开始排队\r\n");
					log.flush();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			//将初始生成的顾客中，因队列长度限制无法加入到队列中的顾客的到达时间间隔适当延长
			while(que_init.size()!=0) {
				if(que_init.get(0).interval_time+que_wait.last_arrival_time>=end_time) {
					break;
				}
				que_init.get(0).add_interval_time(end_time-que_init.get(0).interval_time-que_wait.last_arrival_time);
			}
			//结束服务，仿真时间推进至服务结束时间
			current_time=end_time;
			//记录日志
			try {
				log.write(String.format("%.5f",current_time)+" 第"+cus2.id+"号顾客结束服务离开了\r\n");
				log.flush();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		//关闭日志文件
		try {
			log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
