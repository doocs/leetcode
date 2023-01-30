# [2244. Minimum Rounds to Complete All Tasks](https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks)

[中文文档](/solution/2200-2299/2244.Minimum%20Rounds%20to%20Complete%20All%20Tasks/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>tasks</code>, where <code>tasks[i]</code> represents the difficulty level of a task. In each round, you can complete either 2 or 3 tasks of the <strong>same difficulty level</strong>.</p>

<p>Return <em>the <strong>minimum</strong> rounds required to complete all the tasks, or </em><code>-1</code><em> if it is not possible to complete all the tasks.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tasks = [2,2,3,3,2,4,4,4,4,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> To complete all the tasks, a possible plan is:
- In the first round, you complete 3 tasks of difficulty level 2. 
- In the second round, you complete 2 tasks of difficulty level 3. 
- In the third round, you complete 3 tasks of difficulty level 4. 
- In the fourth round, you complete 2 tasks of difficulty level 4.  
It can be shown that all the tasks cannot be completed in fewer than 4 rounds, so the answer is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tasks = [2,3,3]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is only 1 task of difficulty level 2, but in each round, you can only complete either 2 or 3 tasks of the same difficulty level. Hence, you cannot complete all the tasks, and the answer is -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= tasks[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumRounds(self, tasks: List[int]) -> int:
        cnt = Counter(tasks)
        ans = 0
        for v in cnt.values():
            if v == 1:
                return -1
            ans += v // 3 + (v % 3 != 0)
        return ans
```

### **Java**

```java
class Solution {
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int t : tasks) {
            cnt.merge(t, 1, Integer::sum);
        }
        int ans = 0;
        for (int v : cnt.values()) {
            if (v == 1) {
                return -1;
            }
            ans += v / 3 + (v % 3 == 0 ? 0 : 1);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumRounds(vector<int>& tasks) {
        unordered_map<int, int> cnt;
        for (auto& t : tasks) {
            ++cnt[t];
        }
        int ans = 0;
        for (auto& [_, v] : cnt) {
            if (v == 1) {
                return -1;
            }
            ans += v / 3 + (v % 3 != 0);
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumRounds(tasks []int) int {
	cnt := map[int]int{}
	for _, t := range tasks {
		cnt[t]++
	}
	ans := 0
	for _, v := range cnt {
		if v == 1 {
			return -1
		}
		ans += v / 3
		if v%3 != 0 {
			ans++
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function minimumRounds(tasks: number[]): number {
    const cnt = new Map();
    for (const t of tasks) {
        cnt.set(t, (cnt.get(t) || 0) + 1);
    }
    let ans = 0;
    for (const v of cnt.values()) {
        if (v == 1) {
            return -1;
        }
        ans += Math.floor(v / 3) + (v % 3 === 0 ? 0 : 1);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
