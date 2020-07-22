# [16.21. Sum Swap](https://leetcode-cn.com/problems/sum-swap-lcci)

[中文文档](/lcci/16.21.Sum%20Swap/README.md)

## Description
<p>Given two arrays of integers, find a pair of values (one value from each array) that you can swap to give the two arrays the same sum.</p>



<p>Return an array, where the first element is the element in the first array that will be swapped, and the second element is another one in the second array. If there are more than one answers, return any one of them. If there is no answer, return an empty array.</p>



<p><strong>Example:</strong></p>



<pre>

<strong>Input:</strong> array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]

<strong>Output:</strong> [1, 3]

</pre>



<p><strong>Example:</strong></p>



<pre>

<strong>Input:</strong> array1 = <code>[1, 2, 3], array2 = [4, 5, 6]</code>

<strong>Output: </strong>[]</pre>



<p><strong>Note: </strong></p>



<ul>
	<li><code>1 &lt;= array1.length, array2.length &lt;= 100000</code></li>
</ul>




## Solutions


<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findSwapValues(self, array1: List[int], array2: List[int]) -> List[int]:
        diff = sum(array1) - sum(array2)
        if diff & 1: return []
        diff >>= 1
        s = set(array2)
        for e in array1:
            if (e - diff) in s: return [e, e - diff]
        return []
```

### **Java**

```java
class Solution {
    public int[] findSwapValues(int[] array1, int[] array2) {
        int diff = sum(array1) - sum(array2);
        if ((diff & 1) == 1) {
            return new int[]{};
        }
        diff >>= 1;
        Set<Integer> s = Arrays.stream(array2).boxed().collect(Collectors.toSet());
        for (int e : array1) {
            if (s.contains((e - diff))) {
                return new int[]{e, e - diff};
            }
        }
        return new int[]{};
    }

    private int sum(int[] array) {
        int res = 0;
        for (int e : array) {
            res += e;
        }
        return res;
    }
}
```

### **...**
```

```

<!-- tabs:end -->