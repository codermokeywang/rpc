/**
 * @program: rpc
 * @description:
 * @author: wangwancheng
 * @create: 2021-09-10 12:21
 */
public class CalcService implements CalcInterface {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return a-b;
    }
}