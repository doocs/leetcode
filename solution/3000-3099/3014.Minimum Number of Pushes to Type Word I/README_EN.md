# [3014. Minimum Number of Pushes to Type Word I](https://leetcode.com/problems/minimum-number-of-pushes-to-type-word-i)

[中文文档](/solution/3000-3099/3014.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20I/README.md)

<!-- tags:Greedy,Math,String -->

<!-- difficulty:Easy -->

## Description

<p>You are given a string <code>word</code> containing <strong>distinct</strong> lowercase English letters.</p>

<p>Telephone keypads have keys mapped with <strong>distinct</strong> collections of lowercase English letters, which can be used to form words by pushing them. For example, the key <code>2</code> is mapped with <code>[&quot;a&quot;,&quot;b&quot;,&quot;c&quot;]</code>, we need to push the key one time to type <code>&quot;a&quot;</code>, two times to type <code>&quot;b&quot;</code>, and three times to type <code>&quot;c&quot;</code> <em>.</em></p>

<p>It is allowed to remap the keys numbered <code>2</code> to <code>9</code> to <strong>distinct</strong> collections of letters. The keys can be remapped to <strong>any</strong> amount of letters, but each letter <strong>must</strong> be mapped to <strong>exactly</strong> one key. You need to find the <strong>minimum</strong> number of times the keys will be pushed to type the string <code>word</code>.</p>

<p>Return <em>the <strong>minimum</strong> number of pushes needed to type </em><code>word</code> <em>after remapping the keys</em>.</p>

<p>An example mapping of letters to keys on a telephone keypad is given below. Note that <code>1</code>, <code>*</code>, <code>#</code>, and <code>0</code> do <strong>not</strong> map to any letters.</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3014.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20I/images/keypaddesc.png" style="width: 329px; height: 313px;" />
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3014.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20I/images/keypadv1e1.png" style="width: 329px; height: 313px;" />
<pre>
<strong>Input:</strong> word = &quot;abcde&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The remapped keypad given in the image provides the minimum cost.
&quot;a&quot; -&gt; one push on key 2
&quot;b&quot; -&gt; one push on key 3
&quot;c&quot; -&gt; one push on key 4
&quot;d&quot; -&gt; one push on key 5
&quot;e&quot; -&gt; one push on key 6
Total cost is 1 + 1 + 1 + 1 + 1 = 5.
It can be shown that no other mapping can provide a lower cost.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3000-3099/3014.Minimum%20Number%20of%20Pushes%20to%20Type%20Word%20I/images/keypadv1e2.png" style="width: 329px; height: 313px;" />
<pre>
<strong>Input:</strong> word = &quot;xycdefghij&quot;
<strong>Output:</strong> 12
<strong>Explanation:</strong> The remapped keypad given in the image provides the minimum cost.
&quot;x&quot; -&gt; one push on key 2
&quot;y&quot; -&gt; two pushes on key 2
&quot;c&quot; -&gt; one push on key 3
&quot;d&quot; -&gt; two pushes on key 3
&quot;e&quot; -&gt; one push on key 4
&quot;f&quot; -&gt; one push on key 5
&quot;g&quot; -&gt; one push on key 6
&quot;h&quot; -&gt; one push on key 7
&quot;i&quot; -&gt; one push on key 8
&quot;j&quot; -&gt; one push on key 9
Total cost is 1 + 2 + 1 + 2 + 1 + 1 + 1 + 1 + 1 + 1 = 12.
It can be shown that no other mapping can provide a lower cost.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 26</code></li>
	<li><code>word</code> consists of lowercase English letters.</li>
	<li>All letters in <code>word</code> are distinct.</li>
</ul>

## Solutions

### Solution 1: Greedy Algorithm

We notice that all the letters in the string $word$ are different. Therefore, we can greedily distribute the letters evenly across the $8$ keys to minimize the number of key presses.

The time complexity is $O(n / 8)$, where $n$ is the length of the string $word$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minimumPushes(self, word: str) -> int:
        n = len(word)
        ans, k = 0, 1
        for _ in range(n // 8):
            ans += k * 8
            k += 1
        ans += k * (n % 8)
        return ans
```

```java
class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int ans = 0, k = 1;
        for (int i = 0; i < n / 8; ++i) {
            ans += k * 8;
            ++k;
        }
        ans += k * (n % 8);
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minimumPushes(string word) {
        int n = word.size();
        int ans = 0, k = 1;
        for (int i = 0; i < n / 8; ++i) {
            ans += k * 8;
            ++k;
        }
        ans += k * (n % 8);
        return ans;
    }
};
```

```go
func minimumPushes(word string) (ans int) {
	n := len(word)
	k := 1
	for i := 0; i < n/8; i++ {
		ans += k * 8
		k++
	}
	ans += k * (n % 8)
	return
}
```

```ts
function minimumPushes(word: string): number {
    const n = word.length;
    let ans = 0;
    let k = 1;
    for (let i = 0; i < ((n / 8) | 0); ++i) {
        ans += k * 8;
        ++k;
    }
    ans += k * (n % 8);
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
