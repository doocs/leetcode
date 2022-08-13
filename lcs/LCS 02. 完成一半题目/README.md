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

统计各个问题类型出现的次数，按照次数降序排列。

然后依次选择问题类型，直至满足条件。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def halfQuestions(self, questions: List[int]) -> int:
        counter = Counter(questions)
        n = len(questions) >> 1
        ans = 0
        for i, v in counter.most_common():
            ans += 1
            if v >= n:
                return ans
            n -= v
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int halfQuestions(int[] questions) {
        int[] counter = new int[1010];
        for (int q : questions) {
            ++counter[q];
        }
        Arrays.sort(counter);
        int ans = 0;
        int n = questions.length >> 1;
        for (int i = counter.length - 1; n > 0; --i) {
            ++ans;
            n -= counter[i];
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
        vector<int> counter(1010);
        for (int q : questions) ++counter[q];
        int n = questions.size() >> 1;
        sort(counter.begin(), counter.end());
        int ans = 0;
        for (int i = counter.size() - 1; n > 0; --i) {
            ++ans;
            n -= counter[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func halfQuestions(questions []int) int {
	counter := make([]int, 1010)
	for _, q := range questions {
		counter[q]++
	}
	n := len(questions) >> 1
	sort.Ints(counter)
	ans := 0
	for i := len(counter) - 1; n > 0; i-- {
		ans++
		n -= counter[i]
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[]} questions
 * @return {number}
 */
var halfQuestions = function (questions) {
    let counter = new Array(1010).fill(0);
    for (const q of questions) {
        ++counter[q];
    }
    counter.sort((a, b) => b - a);
    let ans = 0;
    let n = questions.length >> 1;
    for (let i = 0; n > 0; ++i) {
        ++ans;
        n -= counter[i];
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
