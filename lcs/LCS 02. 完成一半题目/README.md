# [LCS 02. 完成一半题目](https://leetcode.cn/problems/WqXACV/)

## 题目描述

<!-- 这里写题目描述 -->

有 `N` 位扣友参加了微软与力扣举办了「以扣会友」线下活动。主办方提供了 `2*N` 道题目，整型数组 `questions` 中每个数字对应了每道题目所涉及的知识点类型。
若每位扣友选择不同的一题，请返回被选的 `N` 道题目至少包含多少种知识点类型。

**示例 1：**

> 输入：`questions = [2,1,6,2]`
>
> 输出：`1`
>
> 解释：有 2 位扣友在 4 道题目中选择 2 题。
> 可选择完成知识点类型为 2 的题目时，此时仅一种知识点类型
> 因此至少包含 1 种知识点类型。

**示例 2：**

> 输入：`questions = [1,5,1,3,4,5,2,5,3,3,8,6]`
>
> 输出：`2`
>
> 解释：有 6 位扣友在 12 道题目中选择题目，需要选择 6 题。
> 选择完成知识点类型为 3、5 的题目，因此至少包含 2 种知识点类型。

**提示：**

-   `questions.length == 2*n`
-   `2 <= questions.length <= 10^5`
-   `1 <= questions[i] <= 1000`

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 排序**

我们可以用哈希表或数组 `cnt` 统计每种知识点类型的题目数量，然后对 `cnt` 进行排序，从大到小遍历 `cnt`，直到遍历的题目数量之和大于等于 `n` 即可，此时遍历的次数即为所求。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为 `questions` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def halfQuestions(self, questions: List[int]) -> int:
        cnt = Counter(questions)
        ans, n = 0, len(questions) >> 1
        for _, v in cnt.most_common():
            ans += 1
            n -= v
            if n <= 0:
                break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int halfQuestions(int[] questions) {
        int[] cnt = new int[1010];
        for (int x : questions) {
            ++cnt[x];
        }
        Arrays.sort(cnt);
        int ans = 0;
        int n = questions.length >> 1;
        for (int i = cnt.length - 1; n > 0; --i) {
            ++ans;
            n -= cnt[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int halfQuestions(vector<int>& questions) {
        int cnt[1001]{};
        for (int& x : questions) {
            ++cnt[x];
        }
        sort(cnt, cnt + 1001);
        int ans = 0, n = questions.size() / 2;
        for (int i = 1000; n > 0; --i) {
            ++ans;
            n -= cnt[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func halfQuestions(questions []int) (ans int) {
	cnt := make([]int, 1010)
	for _, x := range questions {
		cnt[x]++
	}
	n := len(questions) >> 1
	sort.Ints(cnt)
	for i := len(cnt) - 1; n > 0; i-- {
		ans++
		n -= cnt[i]
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} questions
 * @return {number}
 */
var halfQuestions = function (questions) {
    const cnt = new Array(1010).fill(0);
    for (const x of questions) {
        ++cnt[x];
    }
    cnt.sort((a, b) => b - a);
    let ans = 0;
    let n = questions.length >> 1;
    for (let i = 0; n > 0; ++i) {
        ++ans;
        n -= cnt[i];
    }
    return ans;
};
```

### **TypeScript**

```ts
function halfQuestions(questions: number[]): number {
    const cnt = new Array(1010).fill(0);
    for (const x of questions) {
        ++cnt[x];
    }
    cnt.sort((a, b) => b - a);
    let ans = 0;
    let n = questions.length >> 1;
    for (let i = 0; n > 0; ++i) {
        ++ans;
        n -= cnt[i];
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
