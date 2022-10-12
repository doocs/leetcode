# [893. Groups of Special-Equivalent Strings](https://leetcode.com/problems/groups-of-special-equivalent-strings)

[中文文档](/solution/0800-0899/0893.Groups%20of%20Special-Equivalent%20Strings/README.md)

## Description

<p>You are given an array of strings of the same length <code>words</code>.</p>

<p>In one <strong>move</strong>, you can swap any two even indexed characters or any two odd indexed characters of a string <code>words[i]</code>.</p>

<p>Two strings <code>words[i]</code> and <code>words[j]</code> are <strong>special-equivalent</strong> if after any number of moves, <code>words[i] == words[j]</code>.</p>

<ul>
	<li>For example, <code>words[i] = &quot;zzxy&quot;</code> and <code>words[j] = &quot;xyzz&quot;</code> are <strong>special-equivalent</strong> because we may make the moves <code>&quot;zzxy&quot; -&gt; &quot;xzzy&quot; -&gt; &quot;xyzz&quot;</code>.</li>
</ul>

<p>A <strong>group of special-equivalent strings</strong> from <code>words</code> is a non-empty subset of words such that:</p>

<ul>
	<li>Every pair of strings in the group are special equivalent, and</li>
	<li>The group is the largest size possible (i.e., there is not a string <code>words[i]</code> not in the group such that <code>words[i]</code> is special-equivalent to every string in the group).</li>
</ul>

<p>Return <em>the number of <strong>groups of special-equivalent strings</strong> from </em><code>words</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abcd&quot;,&quot;cdab&quot;,&quot;cbad&quot;,&quot;xyzz&quot;,&quot;zzxy&quot;,&quot;zzyx&quot;]
<strong>Output:</strong> 3
<strong>Explanation:</strong> 
One group is [&quot;abcd&quot;, &quot;cdab&quot;, &quot;cbad&quot;], since they are all pairwise special equivalent, and none of the other strings is all pairwise special equivalent to these.
The other two groups are [&quot;xyzz&quot;, &quot;zzxy&quot;] and [&quot;zzyx&quot;].
Note that in particular, &quot;zzxy&quot; is not special equivalent to &quot;zzyx&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;abc&quot;,&quot;acb&quot;,&quot;bac&quot;,&quot;bca&quot;,&quot;cab&quot;,&quot;cba&quot;]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>words[i]</code> consist of lowercase English letters.</li>
	<li>All the strings are of the same length.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numSpecialEquivGroups(self, words: List[str]) -> int:
        s = {''.join(sorted(word[::2]) + sorted(word[1::2])) for word in words}
        return len(s)
```

### **Java**

```java
class Solution {
    public int numSpecialEquivGroups(String[] words) {
        Set<String> s = new HashSet<>();
        for (String word : words) {
            s.add(convert(word));
        }
        return s.size();
    }

    private String convert(String word) {
        List<Character> a = new ArrayList<>();
        List<Character> b = new ArrayList<>();
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (i % 2 == 0) {
                a.add(ch);
            } else {
                b.add(ch);
            }
        }
        Collections.sort(a);
        Collections.sort(b);
        StringBuilder sb = new StringBuilder();
        for (char c : a) {
            sb.append(c);
        }
        for (char c : b) {
            sb.append(c);
        }
        return sb.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numSpecialEquivGroups(vector<string>& words) {
        unordered_set<string> s;
        for (auto& word : words) {
            string a = "", b = "";
            for (int i = 0; i < word.size(); ++i) {
                if (i & 1)
                    a += word[i];
                else
                    b += word[i];
            }
            sort(a.begin(), a.end());
            sort(b.begin(), b.end());
            s.insert(a + b);
        }
        return s.size();
    }
};
```

### **Go**

```go
func numSpecialEquivGroups(words []string) int {
	s := map[string]bool{}
	for _, word := range words {
		a, b := []rune{}, []rune{}
		for i, c := range word {
			if i&1 == 1 {
				a = append(a, c)
			} else {
				b = append(b, c)
			}
		}
		sort.Slice(a, func(i, j int) bool {
			return a[i] < a[j]
		})
		sort.Slice(b, func(i, j int) bool {
			return b[i] < b[j]
		})
		s[string(a)+string(b)] = true
	}
	return len(s)
}
```

### **...**

```

```

<!-- tabs:end -->
