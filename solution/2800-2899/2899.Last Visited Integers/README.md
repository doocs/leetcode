# [2899. 上一个遍历的整数](https://leetcode.cn/problems/last-visited-integers)

[English Version](/solution/2800-2899/2899.Last%20Visited%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串数组&nbsp;<code>words</code>&nbsp;，其中&nbsp;<code>words[i]</code>&nbsp;要么是一个字符串形式的正整数，要么是字符串&nbsp;<code>"prev"</code>&nbsp;。</p>

<p>我们从数组的开头开始遍历，对于 <code>words</code>&nbsp;中的每个&nbsp;<code>"prev"</code>&nbsp;字符串，找到 <code>words</code>&nbsp;中的 <strong>上一个遍历的整数</strong>&nbsp;，定义如下：</p>

<ul>
	<li><code>k</code>&nbsp;表示到当前位置为止的连续&nbsp;<code>"prev"</code>&nbsp;字符串数目（包含当前字符串），令下标从&nbsp;<strong>0</strong>&nbsp;开始的&nbsp;<strong>整数</strong> 数组&nbsp;<code>nums</code>&nbsp;表示目前为止遍历过的所有整数，同时用&nbsp;<code>nums_reverse</code>&nbsp;表示&nbsp;<code>nums</code>&nbsp;反转得到的数组，那么当前 <code>"prev"</code>&nbsp;对应的 <strong>上一个遍历的整数</strong>&nbsp;是&nbsp;<code>nums_reverse</code>&nbsp;数组中下标为 <code>(k - 1)</code>&nbsp;的整数。</li>
	<li>如果&nbsp;<code>k</code>&nbsp;比目前为止遍历过的整数数目 <strong>更多</strong>&nbsp;，那么上一个遍历的整数为&nbsp;<code>-1</code>&nbsp;。</li>
</ul>

<p>请你返回一个整数数组，包含所有上一个遍历的整数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b><code>words</code> = ["1","2","prev","prev","prev"]
<b>输出：</b>[2,1,-1]
<b>解释：</b>
对于下标为 2 处的 "prev" ，上一个遍历的整数是 2 ，因为连续 "prev" 数目为 1 ，同时在数组 reverse_nums 中，第一个元素是 2 。
对于下标为 3 处的 "prev" ，上一个遍历的整数是 1 ，因为连续 "prev" 数目为 2 ，同时在数组 reverse_nums 中，第二个元素是 1 。
对于下标为 4 处的 "prev" ，上一个遍历的整数是 -1 ，因为连续 "prev" 数目为 3 ，但总共只遍历过 2 个整数。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b><code>words</code> = ["1","prev","2","prev","prev"]
<b>输出：</b>[1,2,1]
<strong>解释：</strong>
对于下标为 1 处的 "prev" ，上一个遍历的整数是 1 。
对于下标为 3 处的 "prev" ，上一个遍历的整数是 2 。
对于下标为 4 处的 "prev" ，上一个遍历的整数是 1 ，因为连续 "prev"<strong>&nbsp;</strong>数目为 2 ，同时在数组 reverse_nums 中，第二个元素是 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>words[i] == "prev"</code>&nbsp;或&nbsp;<code>1 &lt;= int(words[i]) &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们直接根据题意模拟即可。在实现上，我们使用一个数组 $nums$ 来存储遍历过的整数，使用一个整数 $k$ 来记录当前连续的 $prev$ 字符串数目。如果当前字符串是 $prev$，那么我们就从 $nums$ 中取出第 $|nums| - k$ 个整数，如果不存在，那么就返回 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $words$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
                let i = nums.len() as i32 - k;
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
