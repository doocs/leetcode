# [2899. Last Visited Integers](https://leetcode.com/problems/last-visited-integers)

[中文文档](/solution/2800-2899/2899.Last%20Visited%20Integers/README.md)

## Description

<p>Given a <strong>0-indexed</strong> array of strings <code>words</code> where <code>words[i]</code> is either a positive integer represented as a string or the string <code>&quot;prev&quot;</code>.</p>

<p>Start iterating from the beginning of the array; for every <code>&quot;prev&quot;</code> string seen in <code>words</code>, find the <strong>last visited integer</strong> in <code>words</code> which is defined as follows:</p>

<ul>
	<li>Let <code>k</code> be the number of consecutive <code>&quot;prev&quot;</code> strings seen so far (containing the current string). Let <code>nums</code> be the <strong>0-indexed </strong>array of <strong>integers</strong> seen so far and <code>nums_reverse</code> be the reverse of <code>nums</code>, then the integer at <code>(k - 1)<sup>th</sup></code> index of <code>nums_reverse</code> will be the <strong>last visited integer</strong> for this <code>&quot;prev&quot;</code>.</li>
	<li>If <code>k</code> is <strong>greater</strong> than the total visited integers, then the last visited integer will be <code>-1</code>.</li>
</ul>

<p>Return <em>an integer array containing the last visited integers.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;1&quot;,&quot;2&quot;,&quot;prev&quot;,&quot;prev&quot;,&quot;prev&quot;]
<strong>Output:</strong> [2,1,-1]
<strong>Explanation:</strong> 
For &quot;prev&quot; at index = 2, last visited integer will be 2 as here the number of consecutive &quot;prev&quot; strings is 1, and in the array reverse_nums, 2 will be the first element.
For &quot;prev&quot; at index = 3, last visited integer will be 1 as there are a total of two consecutive &quot;prev&quot; strings including this &quot;prev&quot; which are visited, and 1 is the second last visited integer.
For &quot;prev&quot; at index = 4, last visited integer will be -1 as there are a total of three consecutive &quot;prev&quot; strings including this &quot;prev&quot; which are visited, but the total number of integers visited is two.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;1&quot;,&quot;prev&quot;,&quot;2&quot;,&quot;prev&quot;,&quot;prev&quot;]
<strong>Output:</strong> [1,2,1]
<strong>Explanation:</strong>
For &quot;prev&quot; at index = 1, last visited integer will be 1.
For &quot;prev&quot; at index = 3, last visited integer will be 2.
For &quot;prev&quot; at index = 4, last visited integer will be 1 as there are a total of two consecutive &quot;prev&quot; strings including this &quot;prev&quot; which are visited, and 1 is the second last visited integer.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>words[i] == &quot;prev&quot;</code> or <code>1 &lt;= int(words[i]) &lt;= 100</code></li>
</ul>

## Solutions

**Solution 1: Simulation**

We can directly simulate according to the problem statement. In the implementation, we use an array $nums$ to store the traversed integers, and an integer $k$ to record the current number of consecutive $prev$ strings. If the current string is $prev$, we take out the $|nums| - k-th$ integer from $nums$. If it does not exist, we return $-1$.

The time complexity is $O(n)$, where $n$ is the length of the array $words$. The space complexity is $O(n)$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def lastVisitedIntegers(self, words: List[str]) -> List[int]:
        nums = []
        ans = []
        k = 0
        for w in words:
            if w == "prev":
                k += 1
                i = len(nums) - k
                ans.append(-1 if i < 0 else nums[i])
            else:
                k = 0
                nums.append(int(w))
        return ans
```

### **Java**

```java
class Solution {
    public List<Integer> lastVisitedIntegers(List<String> words) {
        List<Integer> nums = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int k = 0;
        for (var w : words) {
            if ("prev".equals(w)) {
                ++k;
                int i = nums.size() - k;
                ans.add(i < 0 ? -1 : nums.get(i));
            } else {
                k = 0;
                nums.add(Integer.valueOf(w));
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> lastVisitedIntegers(vector<string>& words) {
        vector<int> nums;
        vector<int> ans;
        int k = 0;
        for (auto& w : words) {
            if (w == "prev") {
                ++k;
                int i = nums.size() - k;
                ans.push_back(i < 0 ? -1 : nums[i]);
            } else {
                k = 0;
                nums.push_back(stoi(w));
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func lastVisitedIntegers(words []string) (ans []int) {
	nums := []int{}
	k := 0
	for _, w := range words {
		if w == "prev" {
			k++
			i := len(nums) - k
			if i < 0 {
				ans = append(ans, -1)
			} else {
				ans = append(ans, nums[i])
			}
		} else {
			k = 0
			x, _ := strconv.Atoi(w)
			nums = append(nums, x)
		}
	}
	return
}
```

### **TypeScript**

```ts
function lastVisitedIntegers(words: string[]): number[] {
    const nums: number[] = [];
    const ans: number[] = [];
    let k = 0;
    for (const w of words) {
        if (w === 'prev') {
            ++k;
            const i = nums.length - k;
            ans.push(i < 0 ? -1 : nums[i]);
        } else {
            k = 0;
            nums.push(+w);
        }
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn last_visited_integers(words: Vec<String>) -> Vec<i32> {
        let mut nums: Vec<i32> = Vec::new();
        let mut ans: Vec<i32> = Vec::new();
        let mut k = 0;

        for w in words {
            if w == "prev" {
                k += 1;
                let i = (nums.len() as i32) - k;
                ans.push(if i < 0 { -1 } else { nums[i as usize] });
            } else {
                k = 0;
                nums.push(w.parse::<i32>().unwrap());
            }
        }

        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
