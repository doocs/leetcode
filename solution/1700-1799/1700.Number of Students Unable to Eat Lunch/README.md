# [1700. 无法吃午餐的学生数量](https://leetcode.cn/problems/number-of-students-unable-to-eat-lunch)

[English Version](/solution/1700-1799/1700.Number%20of%20Students%20Unable%20to%20Eat%20Lunch/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>学校的自助午餐提供圆形和方形的三明治，分别用数字 <code>0</code> 和 <code>1</code> 表示。所有学生站在一个队列里，每个学生要么喜欢圆形的要么喜欢方形的。<br>
餐厅里三明治的数量与学生的数量相同。所有三明治都放在一个 <strong>栈</strong> 里，每一轮：</p>

<ul>
	<li>如果队列最前面的学生 <strong>喜欢</strong> 栈顶的三明治，那么会 <strong>拿走它</strong> 并离开队列。</li>
	<li>否则，这名学生会 <strong>放弃这个三明治</strong> 并回到队列的尾部。</li>
</ul>

<p>这个过程会一直持续到队列里所有学生都不喜欢栈顶的三明治为止。</p>

<p>给你两个整数数组 <code>students</code> 和 <code>sandwiches</code> ，其中 <code>sandwiches[i]</code> 是栈里面第 <code>i<sup>​​​​​​</sup></code> 个三明治的类型（<code>i = 0</code> 是栈的顶部）， <code>students[j]</code> 是初始队列里第 <code>j<sup>​​​​​​</sup></code> 名学生对三明治的喜好（<code>j = 0</code> 是队列的最开始位置）。请你返回无法吃午餐的学生数量。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>students = [1,1,0,0], sandwiches = [0,1,0,1]
<b>输出：</b>0<strong> 
解释：</strong>
- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,0,0,1]。
- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,0,1,1]。
- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [0,1,1]，三明治栈为 sandwiches = [1,0,1]。
- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [1,1,0]。
- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1,0]，三明治栈为 sandwiches = [0,1]。
- 最前面的学生放弃最顶上的三明治，并回到队列的末尾，学生队列变为 students = [0,1]。
- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = [1]，三明治栈为 sandwiches = [1]。
- 最前面的学生拿走最顶上的三明治，剩余学生队列为 students = []，三明治栈为 sandwiches = []。
所以所有学生都有三明治吃。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
<b>输出：</b>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= students.length, sandwiches.length &lt;= 100</code></li>
	<li><code>students.length == sandwiches.length</code></li>
	<li><code>sandwiches[i]</code> 要么是 <code>0</code> ，要么是 <code>1</code> 。</li>
	<li><code>students[i]</code> 要么是 <code>0</code> ，要么是 <code>1</code> 。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

我们观察发现，学生位置可调整，而三明治位置不可调整。也就是说，若前面的三明治没被拿走，则往后的所有三明治也无法被拿走。

因此，我们先用计数器 `cnt` 统计学生喜欢的三明治种类和对应的数量。

然后遍历三明治，若在 `cnt` 中找不到喜欢此三明治的学生，说明后面的三明治也无法被拿走，返回当前剩余的学生数量。

遍历

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为三明治数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countStudents(self, students: List[int], sandwiches: List[int]) -> int:
        cnt = Counter(students)
        for v in sandwiches:
            if cnt[v] == 0:
                return cnt[v ^ 1]
            cnt[v] -= 1
        return 0
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int[] cnt = new int[2];
        for (int v : students) {
            ++cnt[v];
        }
        for (int v : sandwiches) {
            if (cnt[v]-- == 0) {
                return cnt[v ^ 1];
            }
        }
        return 0;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countStudents(vector<int>& students, vector<int>& sandwiches) {
        int cnt[2] = {0};
        for (int& v : students) ++cnt[v];
        for (int& v : sandwiches) {
            if (cnt[v]-- == 0) {
                return cnt[v ^ 1];
            }
        }
        return 0;
    }
};
```

### **Go**

```go
func countStudents(students []int, sandwiches []int) int {
	cnt := [2]int{}
	for _, v := range students {
		cnt[v]++
	}
	for _, v := range sandwiches {
		if cnt[v] == 0 {
			return cnt[v^1]
		}
		cnt[v]--
	}
	return 0
}
```

### **C**

```c
int countStudents(int *students, int studentsSize, int *sandwiches, int sandwichesSize) {
    int count[2] = {0};
    for (int i = 0; i < studentsSize; i++) {
        count[students[i]]++;
    }
    for (int i = 0; i < sandwichesSize; i++) {
        int j = sandwiches[i];
        if (count[j] == 0) {
            return count[j ^ 1];
        }
        count[j]--;
    }
    return 0;
}
```

### **TypeScript**

```ts
function countStudents(students: number[], sandwiches: number[]): number {
    const count = [0, 0];
    for (const v of students) {
        count[v]++;
    }
    for (const v of sandwiches) {
        if (count[v] === 0) {
            return count[v ^ 1];
        }
        count[v]--;
    }
    return 0;
}
```

### **Rust**

```rust
impl Solution {
    pub fn count_students(students: Vec<i32>, sandwiches: Vec<i32>) -> i32 {
        let mut count = [0, 0];
        for &v in students.iter() {
            count[v as usize] += 1;
        }
        for &v in sandwiches.iter() {
            let v = v as usize;
            if count[v as usize] == 0 {
                return count[v ^ 1];
            }
            count[v] -= 1;
        }
        0
    }
}
```

### **...**

```

```

<!-- tabs:end -->
