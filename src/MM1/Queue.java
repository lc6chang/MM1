package MM1;

import java.util.ArrayList;

public class Queue {
	public ArrayList<Customer> que;//等待队列
	public int max_len;//等待队列最大长度
	public double last_arrival_time;//上一个顾客到达的时间
	public double total_of_delays;//总延迟时间
	public int num_cus_delayed;//被延迟顾客总数
	
	public Queue(int _max_len) {//初始化各项属性
		que=new ArrayList<Customer>();
		max_len=_max_len;
		last_arrival_time=0.0;
		total_of_delays=0.0;
		num_cus_delayed=0;
	}
	//以下方法均是为了减少代码长度，方便阅读单独添加的
	public int size() {//返回队列长度
		return que.size();
	}
	public void add(Customer cus) {//向队列末尾添加一个顾客
		que.add(cus);
	}
	public Customer get(int i) {//读取队列第i个顾客
		return que.get(i);
	}
	public Customer remove(int i) {//移除队列第i个顾客
		return que.remove(i);
	}
}
