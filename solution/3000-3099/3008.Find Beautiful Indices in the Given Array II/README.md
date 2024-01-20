# [3008. 找出数组中的美丽下标 II](https://leetcode.cn/problems/find-beautiful-indices-in-the-given-array-ii)

[English Version](/solution/3000-3099/3008.Find%20Beautiful%20Indices%20in%20the%20Given%20Array%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>s</code>&nbsp;、字符串&nbsp;<code>a</code>&nbsp;、字符串&nbsp;<code>b</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>如果下标 <code>i</code>&nbsp;满足以下条件，则认为它是一个 <strong>美丽下标</strong>&nbsp;：</p>

<ul>
	<li><code>0 &lt;= i &lt;= s.length - a.length</code></li>
	<li><code>s[i..(i + a.length - 1)] == a</code></li>
	<li>存在下标&nbsp;<code>j</code>&nbsp;使得：
	<ul>
		<li><code>0 &lt;= j &lt;= s.length - b.length</code></li>
		<li><code>s[j..(j + b.length - 1)] == b</code></li>
		<li><code>|j - i| &lt;= k</code></li>
	</ul>
	</li>
</ul>

<p>以数组形式按<strong>&nbsp;从小到大排序&nbsp;</strong>返回美丽下标。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "isawsquirrelnearmysquirrelhouseohmy", a = "my", b = "squirrel", k = 15
<strong>输出：</strong>[16,33]
<strong>解释：</strong>存在 2 个美丽下标：[16,33]。
- 下标 16 是美丽下标，因为 s[16..17] == "my" ，且存在下标 4 ，满足 s[4..11] == "squirrel" 且 |16 - 4| &lt;= 15 。
- 下标 33 是美丽下标，因为 s[33..34] == "my" ，且存在下标 18 ，满足 s[18..25] == "squirrel" 且 |33 - 18| &lt;= 15 。
因此返回 [16,33] 作为结果。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>s = "abcd", a = "a", b = "a", k = 4
<b>输出：</b>[0]
<strong>解释：</strong>存在 1 个美丽下标：[0]。
- 下标 0 是美丽下标，因为 s[0..0] == "a" ，且存在下标 0 ，满足 s[0..0] == "a" 且 |0 - 0| &lt;= 4 。
因此返回 [0] 作为结果。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= a.length, b.length &lt;= 5 * 10<sup>5</sup></code></li>
	<li><code>s</code>、<code>a</code>、和&nbsp;<code>b</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

```cpp
class Solution {
public:
    vector<int> beautifulIndices(string s, string patternA, string patternB, int k) {
        vector<int> beautifulIndicesA = kmpSearch(s, patternA);
        vector<int> beautifulIndicesB = kmpSearch(s, patternB);

        sort(beautifulIndicesB.begin(), beautifulIndicesB.end());

        vector<int> result;
        for (int indexA : beautifulIndicesA) {
            int left = lower_bound(beautifulIndicesB.begin(), beautifulIndicesB.end(), indexA - k) - beautifulIndicesB.begin();
            int right = lower_bound(beautifulIndicesB.begin(), beautifulIndicesB.end(), indexA + k + patternB.length()) - beautifulIndicesB.begin();

            left = (left >= 0) ? left : -(left + 1);
            right = (right >= 0) ? right : -(right + 1);

            for (int indexB = left; indexB < right; indexB++) {
                if (abs(beautifulIndicesB[indexB] - indexA) <= k) {
                    result.push_back(indexA);
                    break;
                }
            }
        }

        return result;
    }

private:
    vector<int> kmpSearch(string text, string pattern) {
        vector<int> indices;
        vector<int> pi = computePrefixFunction(pattern);

        int q = 0;
        for (int i = 0; i < text.length(); i++) {
            while (q > 0 && pattern[q] != text[i]) {
                q = pi[q - 1];
            }
            if (pattern[q] == text[i]) {
                q++;
            }
            if (q == pattern.length()) {
                indices.push_back(i - q + 1);
                q = pi[q - 1];
            }
        }

        return indices;
    }

    vector<int> computePrefixFunction(string pattern) {
        int m = pattern.length();
        vector<int> pi(m, 0);
        int k = 0;
        for (int q = 1; q < m; q++) {
            while (k > 0 && pattern[k] != pattern[q]) {
                k = pi[k - 1];
            }
            if (pattern[k] == pattern[q]) {
                k++;
            }
            pi[q] = k;
        }
        return pi;
    }
};
```

<!-- end -->
