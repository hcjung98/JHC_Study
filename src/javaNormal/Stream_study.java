package javaNormal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream_study {
/*    자바8부터 Stream 을 사용 할 수 있습니다.

    기존에 자바 컬렉션이나 배열의 원소를 가공할떄, for문, foreach 등으로 원소 하나씩 골라내여 가공을 하였다면,
    Stream 을 이용하여 람다함수형식으로 간결하고 깔끔하게 요소들의 처리가 가능g.
    스트림 사용법을 간단하게 알아보겠습니다.

    배열의 원소를 가공하는데 있어
    map, filter, sorted 등 이 있습니다.

    map은 요소들을 특정조건에 해당하는 값으로 변환해 줍니다.
    요소들을 대,소문자 변형 등 의 작업을 하고 싶을떄 사용 가능 합니다.

    filter는 요소들을 조건에 따라 걸러내는 작업을 해줍니다.
    길이의 제한, 특정문자포함 등 의 작업을 하고 싶을때 사용 가능합니다.

    sorted는 요소들을 정렬해주는 작업을 해줍니다.

    요소들의 가공이 끝났다면 리턴해줄 결과를 collect 를 통해 만들어줍니다.    */

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Apple", "Banana", "Melon", "Grape", "Strawberry"));

        list.stream().map(String::toString).forEach(s -> System.out.println(s));
        System.out.println();

        List<String> listA = list.stream().map(s -> s.toUpperCase()).collect(Collectors.toList()); // 1)번 방법
        System.out.println(listA);

        list.stream().map(String::toUpperCase);  // 2)번 방법
        System.out.println(list);

        list.stream().map(String::toUpperCase).forEach(s -> System.out.println(s));
        System.out.println(list.stream().map(s -> s.toUpperCase()).collect(Collectors.joining(" ")));
        System.out.println(list.stream().map(s -> s.toUpperCase()).collect(Collectors.toList()));
        System.out.println(list.stream().map(String::toUpperCase).collect(Collectors.toList()));


        List<String> listB = list.stream().filter(s -> s.length() > 5).collect(Collectors.toList());
        System.out.println(listB);

        System.out.println(list.stream().filter(s -> s.length() > 5).collect(Collectors.joining(" ")));
        System.out.println(list.stream().filter(s -> s.length() > 5).collect(Collectors.toList()));

        System.out.println(list.stream().sorted().collect(Collectors.joining(" ")));


    }


}
