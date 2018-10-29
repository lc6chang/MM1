package MM1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		double v_arrival,v_serve;//存储到达率和服务率
		int num_cus,max_len;//存储顾客人数和队列最大长度
		//输入各项参数
		Scanner in=new Scanner(System.in);
        System.out.println("请输入到达率(人/秒)：");
        v_arrival=in.nextDouble();
        System.out.println("请输入服务率(人/秒)：");
        v_serve=in.nextDouble();
        System.out.println("请输入顾客人数(人)：");
        num_cus=in.nextInt();
        System.out.println("请输入队列最大长度(人)：");
        max_len=in.nextInt();
        in.close();
        
        //创建用于存储生成顾客的队列que_init，以及等待队列que_wait
        ArrayList <Customer> que_init=new ArrayList<Customer>();
        Queue que_wait=new Queue(max_len);
        
        //生成数量为num_cus的顾客，加入que_init
        int i;
        Customer cus;
        for(i=0;i<num_cus;i++) {
        	cus=new Customer(v_arrival,v_serve,i+1);
        	que_init.add(cus);
        }
        
        //创建服务器，开始仿真
        Server ser=new Server();
        ser.Run(que_init, que_wait);
        
        //输出各项统计结果
        System.out.println("顾客到达率(人/秒)："
        				   +String.format("%.5f",num_cus/que_wait.last_arrival_time));
        System.out.println("服务器服务率(人/秒)："
        				   +String.format("%.5f",ser.serve_num/ser.serve_time));
        System.out.println("队列平均顾客数(人)："
        				   +String.format("%.5f",que_wait.total_of_delays/ser.current_time));
        System.out.println("平均等待时间(秒)："
        				   +String.format("%.5f",que_wait.total_of_delays/ser.serve_num));
        System.out.println("服务器利用率："
        				   +String.format("%.5f",ser.serve_time/ser.current_time));
 
	}
}
