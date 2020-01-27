## 1287. 有序数组中出现次数超过 25% 的元素
给你一个非递减的 有序 整数数组，已知这个数组中恰好有一个整数，它的出现次数超过数组元素总数的 25%。

请你找到并返回这个整数。

- JavaScript

```javascript
const findSpecialInteger = function(arr) {
  let count = 0;
  let item = -1;
  for (var i = 0; i < arr.length; i++) {
      if (item == arr[i]) {
          count++;
      } else {
          item = arr[i];
          count = 1;
      }
      if (count > arr.length * 0.25) {
          return item;
      }
  }
  return item;
};
```

- Java

```java
class Solution {
    public int findSpecialInteger(int[] arr) {
        int total = arr.length;
        for (int i = 0; i < total; ++i) {
            if (arr[i] == arr[i + (total >> 2)]) {
                return arr[i];
            }
        }
        return 0;
    }
}
```