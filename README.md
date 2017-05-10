# MST_Algorithm

최소신장트리 알고리즘
+ 프림 알고리즘
+ 크루스칼 알고리즘
   + 서로소 집합 참고 블로그 : http://swlock.blogspot.kr/2016/05/disjoint-set-data-structure-algorithm.html
   + 크루스칼 알고리즘 참고 블로그 : http://swlock.blogspot.kr/2016/05/kruskals-algorithm.html
   
   
- 크루스칼 알고리즘 관련 문제 : https://www.acmicpc.net/problem/1197 // https://www.acmicpc.net/problem/1922
- 처음에 가중치를 낮은 순서로 정렬하는 곳에서 계속 시간초과가 발생했다. 이를 퀵정렬방식으로 바꾸니 해결되었다.
- 2번째 문제의 경우에는 퀵정렬이었지만, 테스트 예시에서 퀵정렬의 최악의 경우가 종종 있어서 n^2이 발생한 것 같음. 그래서 계속 시간초과가 발생하는 것 같아 병합정렬로 교체하였더니 해결됨
