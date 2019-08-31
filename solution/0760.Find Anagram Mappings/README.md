## 找出变位映射

### 问题描述
给定两个列表 A and B，并且 B 是 A 的变位。B 是 A 的变位的意思是 B 由 A 中的元素随机排列生成。

我们希望找出一个从 A 到 B 的索引映射 P 。一个映射 P[i] = j 的意思是 A 中的第 i 个元素出现于 B 中的第 j 个元素上。

列表 A 和 B 可能出现重复元素。如果有多于一种答案，输出任意一种。

例如，给定

```
A = [12, 28, 46, 32, 50]
B = [50, 12, 32, 46, 28]
```

需要返回
```
[1, 4, 3, 2, 0]
```

P[0] = 1 ，因为 A 中的第 0 个元素出现于 B[1]，而且 P[1] = 4 因为 A 中第 1个元素出现于 B[4]，以此类推。

**注：**

1. A, B 有相同的长度，范围为 [1, 100]。
2. A[i], B[i] 都是范围在 [0, 10^5] 的整数。

### 解法
先将 B 转换为 Map（Python 中为字典 dict），其中 key 为 B 中元素，value 为 B 对应元素的下标（索引位置）。之后遍历 A，读取每个元素在 Map 中的 value 值，存到结果列表中。

1. Java 解法

```java
import java.util.*;

class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            map.put(B[i], i);
        }
        int[] res = new int[B.length];
        int j = 0;
        for (int k : A) {
            res[j++] = map.get(k);
        }
        return res;
    }
}
```

2. Python 解法

```python
class Solution:
    def anagramMappings(self, A, B):
        """
        :type A: List[int]
        :type B: List[int]
        :rtype: List[int]
        """
        record = {val: i for i, val in enumerate(B)}
        return [record[val] for val in A]
```