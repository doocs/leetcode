## 删列造序 II

### 问题描述

给定由 `N` 个小写字母字符串组成的数组 `A`，其中每个字符串长度相等。

选取一个删除索引序列，对于 `A` 中的每个字符串，删除对应每个索引处的字符。

比如，有 `A = ["abcdef", "uvwxyz"]`，删除索引序列 `{0, 2, 3}`，删除后 `A` 为`["bef", "vyz"]`。

假设，我们选择了一组删除索引 `D`，那么在执行删除操作之后，最终得到的数组的元素是按 **字典序**（`A[0] <= A[1] <= A[2] ... <= A[A.length - 1]`）排列的，然后请你返回 `D.length` 的最小可能值。

**示例1:**

```
输入：["ca","bb","ac"]
输出：1
解释： 
删除第一列后，A = ["a", "b", "c"]。
现在 A 中元素是按字典排列的 (即，A[0] <= A[1] <= A[2])。
我们至少需要进行 1 次删除，因为最初 A 不是按字典序排列的，所以答案是 1。
```

**示例2:**

```
输入：["xc","yb","za"]
输出：0
解释：
A 的列已经是按字典序排列了，所以我们不需要删除任何东西。
注意 A 的行不需要按字典序排列。
也就是说，A[0][0] <= A[0][1] <= ... 不一定成立。
```

**示例3**

```
输入：["zyx","wvu","tsr"]
输出：3
解释：
我们必须删掉每一列。
```

**提示:**

- `1 <= A.length <= 100`
- `1 <= A[i].length <= 100`

### 解法

#### 基本解法

原题中考虑的是对于某一列，我们是不是应该**删掉**它，那么反过来则可以考虑为：对于某一列，我们**保留**的条件是什么。

* 如果某列 i 可以被保留，那么我们只要保证

  ```
  A[1][i] <= A[2][i] <= ... <= A[n][i] <= A[n + 1][i]
  ```

* 相反地，如果列 i 不能被保留，那么则需要让

  ```
  A[1][i:] <= A[2][i:] <= ... <= A[n][i:] <= A[n + 1][i:]
  ```

  其中 **i:** 表示第 i 列之后的所有字母（不包括 i）

显然，我们需要尽量地保留 i 以做到让我们的删列工作尽可能地简便。因此，这道题需要使用贪心算法。这需要我们在遍历输入的过程中,**考虑新增的每列是否可以让原有已经存在的字符串们保留字典排序**。因此，我们可以得到以下的算法：

```java
class Solution {
    public int minDeletionSize(String[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }
        int len = A.length, wordLen = A[0].length(), res = 0;
        // 初始化空字符串数组，记录可以保留的每列
        String[] curr = new String[len];
        for (int j = 0; j < wordLen; j++) {
            // 对于当前遍历到的列，需要决定其是否可以被保留
            // 使用复制数组来决定当前列是否保留
            String[] temp = Arrays.copyOf(curr, len);
            for (int i = 0; i < len; i++) {
                temp[i] += A[i].charAt(j);
            }
            if (!isSorted(temp)) {
                // 如果未 sorted 则该列需要被删除
                res += 1;
                continue;
            }
            curr = temp;
        }
        return res;
    }
    
    public boolean isSorted(String[] temp) {
        // 判断现有的 string 数组是否已经 sorted
        for (int i = 0; i < temp.length - 1; i++) {
            if (temp[i].compareTo(temp[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }
}
```

该解法的时间复杂度为 O(NM²)，其中 N 为 word 长度，M 为数组长度。空间复杂度为 O(NW)，算法中包含两个复制出来的字符串数组。

#### 优化解法

上述解法需要我们在 isSorted 方法中一直需要遍历现存元素的字符串长度来做到判定该数组是否已经 sorted，有没有更优化的方法让我们可以避免重复的遍历呢？思考下面的情况，对于数组

```
A = ["ar", "ax", "be", "bf", "bg"]
```

我们发现在我们决定可以保留第一个字符之后，后面要保证其 sorted 的条件可以简化为：

```
A[0] <= A[1] and A[2] <= A[3] <= A[4]
```

因此，当我们发现有**严格 sorted** 的字符后，可以设置一个 cut 的标志，表示 cut 两边的字符无需进行比较，并且这个 cut 是一劳永逸的，即当你发现这个 cut 的标志时，后面的遍历就再也不用比较这两个字符串了。因此，我们有以下的优化解法：

```java
class Solution {
    public int minDeletionSize(String[] A) {
        if (A == null || A.length <= 1) {
            return 0;
        }
        int len = A.length, wordLen = A[0].length(), res = 0;
        boolean[] cut = new boolean[len];
        search: for (int j = 0; j < wordLen; j++) {
            // 判断第 j 列是否应当保留
            for (int i = 0; i < len - 1; i++) {
                if (!cut[i] && A[i].charAt(j) > A[i + 1].charAt(j)) {
                    res += 1;
                    continue search;
                }
            }
            // 更新 cut 的信息
            for (int i = 0; i < len - 1; i++) {
                if (A[i].charAt(j) < A[i + 1].charAt(j)) {
                    cut[i] = true;
                }
            }
        }
        return res;
    }
}
```

解法只用到了额外的 cut boolean 数组，空间复杂度为 O(M)，时间复杂度为 O(NM)，其中 N 为 word 长度，M 为数组长度。
