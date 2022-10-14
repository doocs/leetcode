# [1441. Build an Array With Stack Operations](https://leetcode.com/problems/build-an-array-with-stack-operations)

[中文文档](/solution/1400-1499/1441.Build%20an%20Array%20With%20Stack%20Operations/README.md)

## Description

<p>You are given an integer array <code>target</code> and an integer <code>n</code>.</p>

<p>You have an empty stack with the two following operations:</p>

<ul>
	<li><strong><code>&quot;Push&quot;</code></strong>: pushes an integer to the top of the stack.</li>
	<li><strong><code>&quot;Pop&quot;</code></strong>: removes the integer on the top of the stack.</li>
</ul>

<p>You also have a stream of the integers in the range <code>[1, n]</code>.</p>

<p>Use the two stack operations to make the numbers in the stack (from the bottom to the top) equal to <code>target</code>. You should follow the following rules:</p>

<ul>
	<li>If the stream of the integers is not empty, pick the next integer from the stream and push it to the top of the stack.</li>
	<li>If the stack is not empty, pop the integer at the top of the stack.</li>
	<li>If, at any moment, the elements in the stack (from the bottom to the top) are equal to <code>target</code>, do not read new integers from the stream and do not do more operations on the stack.</li>
</ul>

<p>Return <em>the stack operations needed to build </em><code>target</code> following the mentioned rules. If there are multiple valid answers, return <strong>any of them</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = [1,3], n = 3
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;,&quot;Pop&quot;,&quot;Push&quot;]
<strong>Explanation:</strong> Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Pop the integer on the top of the stack. s = [1].
Read 3 from the stream and push it to the stack. s = [1,3].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = [1,2,3], n = 3
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;,&quot;Push&quot;]
<strong>Explanation:</strong> Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Read 3 from the stream and push it to the stack. s = [1,2,3].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = [1,2], n = 4
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;]
<strong>Explanation:</strong> Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Since the stack (from the bottom to the top) is equal to target, we stop the stack operations.
The answers that read integer 3 from the stream are not accepted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 100</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= target[i] &lt;= n</code></li>
	<li><code>target</code> is strictly increasing.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def buildArray(self, target: List[int], n: int) -> List[str]:
        cur, ans = 0, []
        for v in target:
            cur += 1
            while cur < v:
                ans.extend(['Push', 'Pop'])
                cur += 1
            ans.append('Push')
        return ans
```

### **Java**

```java
class Solution {
    public List<String> buildArray(int[] target, int n) {
        int cur = 0;
        List<String> ans = new ArrayList<>();
        for (int v : target) {
            while (++cur < v) {
                ans.add("Push");
                ans.add("Pop");
            }
            ans.add("Push");
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> buildArray(vector<int>& target, int n) {
        int cur = 0;
        vector<string> ans;
        for (int& v : target) {
            while (++cur < v) {
                ans.emplace_back("Push");
                ans.emplace_back("Pop");
            }
            ans.emplace_back("Push");
        }
        return ans;
    }
};
```

### **Go**

```go
func buildArray(target []int, n int) []string {
	cur := 0
	ans := []string{}
	for _, v := range target {
		for cur = cur + 1; cur < v; cur++ {
			ans = append(ans, "Push", "Pop")
		}
		ans = append(ans, "Push")
	}
	return ans
}
```

### **C**

```c
/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
char **buildArray(int *target, int targetSize, int n, int *returnSize) {
    char **res = (char **) malloc(sizeof(char *) * n * 2);
    int cur = 1;
    int i = 0;
    for (int j = 0; j < targetSize; j++) {
        while (++cur < target[j]) {
            res[i] = (char *) malloc(sizeof(char) * 8);
            strcpy(res[i++], "Push");
            res[i] = (char *) malloc(sizeof(char) * 8);
            strcpy(res[i++], "Pop");
        }
        res[i] = (char *) malloc(sizeof(char) * 8);
        strcpy(res[i++], "Push");
    }
    *returnSize = i;
    return res;
}
```

### **TypeScript**

```ts
function buildArray(target: number[], n: number): string[] {
    const res = [];
    let cur = 0;
    for (const num of target) {
        while (++cur < num) {
            res.push('Push', 'Pop');
        }
        res.push('Push');
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn build_array(target: Vec<i32>, n: i32) -> Vec<String> {
        let mut res = Vec::new();
        let mut cur = 1;
        for &num in target.iter() {
            while cur < num {
                res.push("Push");
                res.push("Pop");
                cur += 1;
            }
            res.push("Push");
            cur += 1;
        }
        res.into_iter().map(String::from).collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
