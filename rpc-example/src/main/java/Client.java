import com.wang.rpc.client.RpcClient;

/**
 * @program: rpc
 * @description:
 * @author: wangwancheng
 * @create: 2021-09-10 12:20
 */
public class Client {
    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        CalcInterface proxy = rpcClient.getProxy(CalcInterface.class);
        int add = proxy.add(1, 2);
        int minus = proxy.minus(2, 1);
        System.out.println("add="+ add + ", minus=" + minus);
    }
}