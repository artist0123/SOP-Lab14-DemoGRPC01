package SumClient;

import com.proto.Sum.Sum;
import com.proto.Sum.SumRequest;
import com.proto.Sum.SumResponse;
import com.proto.Sum.SumServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class SumClient {
    public static void main(String[] args) {
        System.out.println("Hello gRPC Sum client");
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 55554)
                .usePlaintext()
                .build();
        System.out.println("Creating stub");

        int num1 = 20;
        int num2 = 30;

        SumServiceGrpc.SumServiceBlockingStub sumClient = SumServiceGrpc.newBlockingStub(channel);
        Sum sum = Sum.newBuilder()
                .setNum1(num1)
                .setNum2(num2)
                .build();

        SumRequest sumRequest = SumRequest.newBuilder().setSum(sum).build();

        SumResponse sumResponse = sumClient.sum(sumRequest);
        System.out.println("Server Output: " + num1 + " + " + num2 + " = " + sumResponse.getResult());

        System.out.println("Shutting down channel");
        channel.shutdown();

    }
}
