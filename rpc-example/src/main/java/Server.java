import com.wang.rpc.server.Rpcserver;

/**
 * @program: rpc
 * @description:
 * @author: wangwancheng
 * @create: 2021-09-10 12:18
 */
public class Server {
    public static void main(String[] args) {
        Rpcserver server = new Rpcserver();
        server.register(CalcService.class,new CalcService());
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}