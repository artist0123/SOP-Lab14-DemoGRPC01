package SumServer;

import com.proto.Sum.*;
import io.grpc.stub.StreamObserver;

public class SumServiceImpl extends SumServiceGrpc.SumServiceImplBase {
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver)
    {
        Sum sum = request.getSum();
        int num1 = sum.getNum1();
        int num2 = sum.getNum2();

        int plus = num1 + num2;
//        String result = "Server Output: " + num1 + " + " + num2 + " = " + plus;
        String result = "" + plus;
        System.out.println(plus);
        SumResponse response = SumResponse.newBuilder().setResult(result).build();

        responseObserver.onNext(response);

        responseObserver.onCompleted();
    }
}
