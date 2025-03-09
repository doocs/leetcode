---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2227.Encrypt%20and%20Decrypt%20Strings/README.md
rating: 1944
source: 第 287 场周赛 Q4
tags:
    - 设计
    - 字典树
    - 数组
    - 哈希表
    - 字符串
---

<!-- problem:start -->

# [2227. 加密解密字符串](https://leetcode.cn/problems/encrypt-and-decrypt-strings)

[English Version](/solution/2200-2299/2227.Encrypt%20and%20Decrypt%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符数组 <code>keys</code> ，由若干 <strong>互不相同</strong> 的字符组成。还有一个字符串数组 <code>values</code> ，内含若干长度为 2 的字符串。另给你一个字符串数组 <code>dictionary</code> ，包含解密后所有允许的原字符串。请你设计并实现一个支持加密及解密下标从 <strong>0</strong> 开始字符串的数据结构。</p>

<p>字符串 <strong>加密</strong> 按下述步骤进行：</p>

<ol>
	<li>对字符串中的每个字符 <code>c</code> ，先从 <code>keys</code> 中找出满足 <code>keys[i] == c</code> 的下标 <code>i</code> 。</li>
	<li>在字符串中，用&nbsp;<code>values[i]</code> 替换字符 <code>c</code> 。</li>
</ol>

<p>请注意，如果&nbsp;<code>keys</code> 中不存在字符串中的字符，则无法执行加密过程，返回空字符串 <code>""</code>。</p>

<p>字符串 <strong>解密</strong> 按下述步骤进行：</p>

<ol>
	<li>将字符串每相邻 2 个字符划分为一个子字符串，对于每个子字符串 <code>s</code> ，找出满足 <code>values[i] == s</code> 的一个下标 <code>i</code> 。如果存在多个有效的 <code>i</code> ，从中选择 <strong>任意</strong> 一个。这意味着一个字符串解密可能得到多个解密字符串。</li>
	<li>在字符串中，用 <code>keys[i]</code> 替换 <code>s</code> 。</li>
</ol>

<p>实现 <code>Encrypter</code> 类：</p>

<ul>
	<li><code>Encrypter(char[] keys, String[] values, String[] dictionary)</code> 用 <code>keys</code>、<code>values</code> 和 <code>dictionary</code> 初始化 <code>Encrypter</code> 类。</li>
	<li><code>String encrypt(String word1)</code> 按上述加密过程完成对 <code>word1</code> 的加密，并返回加密后的字符串。</li>
	<li><code>int decrypt(String word2)</code> 统计并返回可以由 <code>word2</code> 解密得到且出现在 <code>dictionary</code> 中的字符串数目。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
["Encrypter", "encrypt", "decrypt"]
[[['a', 'b', 'c', 'd'], ["ei", "zf", "ei", "am"], ["abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"]], ["abcd"], ["eizfeiam"]]
<strong>输出：</strong>
[null, "eizfeiam", 2]

<strong>解释：</strong>
Encrypter encrypter = new Encrypter([['a', 'b', 'c', 'd'], ["ei", "zf", "ei", "am"], ["abcd", "acbd", "adbc", "badc", "dacb", "cadb", "cbda", "abad"]);
encrypter.encrypt("abcd"); // 返回 "eizfeiam"。 
&nbsp;                          // 'a' 映射为 "ei"，'b' 映射为 "zf"，'c' 映射为 "ei"，'d' 映射为 "am"。
encrypter.decrypt("eizfeiam"); // return 2. 
                              // "ei" 可以映射为 'a' 或 'c'，"zf" 映射为 'b'，"am" 映射为 'd'。 
                              // 因此，解密后可以得到的字符串是 "abad"，"cbad"，"abcd" 和 "cbcd"。 
                              // 其中 2 个字符串，"abad" 和 "abcd"，在 dictionary 中出现，所以答案是 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= keys.length == values.length &lt;= 26</code></li>
	<li><code>values[i].length == 2</code></li>
	<li><code>1 &lt;= dictionary.length &lt;= 100</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 100</code></li>
	<li>所有 <code>keys[i]</code> 和 <code>dictionary[i]</code> <strong>互不相同</strong></li>
	<li><code>1 &lt;= word1.length &lt;= 2000</code></li>
	<li><code>1 &lt;= word2.length &lt;= 200</code></li>
	<li>所有 <code>word1[i]</code> 都出现在 <code>keys</code> 中</li>
	<li><code>word2.length</code> 是偶数</li>
	<li><code>keys</code>、<code>values[i]</code>、<code>dictionary[i]</code>、<code>word1</code> 和 <code>word2</code> 只含小写英文字母</li>
	<li>至多调用 <code>encrypt</code> 和 <code>decrypt</code> <strong>总计</strong> <code>200</code> 次</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

我们用一个哈希表 $\textit{mp}$ 记录每个字符的加密结果，用另一个哈希表 $\textit{cnt}$ 记录每个加密结果出现的次数。

在构造函数中，我们遍历 $\textit{keys}$ 和 $\textit{values}$，将每个字符和其对应的加密结果存入 $\textit{mp}$ 中。然后遍历 $\textit{dictionary}$，统计每个加密结果出现的次数。时间复杂度 $(n + m)$，其中 $n$ 和 $m$ 分别是 $\textit{keys}$ 和 $\textit{dictionary}$ 的长度。

在加密函数中，我们遍历输入字符串 $\textit{word1}$ 的每个字符，查找其加密结果并拼接起来。如果某个字符没有对应的加密结果，说明无法加密，返回空字符串。时间复杂度 $O(k)$，其中 $k$ 是 $\textit{word1}$ 的长度。

在解密函数中，我们直接返回 $\textit{cnt}$ 中 $\textit{word2}$ 对应的次数。时间复杂度 $O(1)$。

空间复杂度 $O(n + m)$。

<!-- tabs:start -->

#### Python3

```python
class Encrypter:
    def __init__(self, keys: List[str], values: List[str], dictionary: List[str]):
        self.mp = dict(zip(keys, values))
        self.cnt = Counter(self.encrypt(v) for v in dictionary)

    def encrypt(self, word1: str) -> str:
        res = []
        for c in word1:
            if c not in self.mp:
                return ''
            res.append(self.mp[c])
        return ''.join(res)

    def decrypt(self, word2: str) -> int:
        return self.cnt[word2]


# Your Encrypter object will be instantiated and called as such:
# obj = Encrypter(keys, values, dictionary)
# param_1 = obj.encrypt(word1)
# param_2 = obj.decrypt(word2)
```

#### Java

```java
class Encrypter {
    private Map<Character, String> mp = new HashMap<>();
    private Map<String, Integer> cnt = new HashMap<>();

    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        for (int i = 0; i < keys.length; ++i) {
            mp.put(keys[i], values[i]);
        }
        for (String w : dictionary) {
            cnt.merge(encrypt(w), 1, Integer::sum);
        }
    }

    public String encrypt(String word1) {
        StringBuilder sb = new StringBuilder();
        for (char c : word1.toCharArray()) {
            if (!mp.containsKey(c)) {
                return "";
            }
            sb.append(mp.get(c));
        }
        return sb.toString();
    }

    public int decrypt(String word2) {
        return cnt.getOrDefault(word2, 0);
    }
}

/**
 * Your Encrypter object will be instantiated and called as such:
 * Encrypter obj = new Encrypter(keys, values, dictionary);
 * String param_1 = obj.encrypt(word1);
 * int param_2 = obj.decrypt(word2);
 */
```

#### C++

```cpp
class Encrypter {
public:
    unordered_map<string, int> cnt;
    unordered_map<char, string> mp;

    Encrypter(vector<char>& keys, vector<string>& values, vector<string>& dictionary) {
        for (int i = 0; i < keys.size(); ++i) {
            mp[keys[i]] = values[i];
        }
        for (auto v : dictionary) {
            cnt[encrypt(v)]++;
        }
    }

    string encrypt(string word1) {
        string res = "";
        for (char c : word1) {
            if (!mp.count(c)) {
                return "";
            }
            res += mp[c];
        }
        return res;
    }

    int decrypt(string word2) {
        return cnt[word2];
    }
};

/**
 * Your Encrypter object will be instantiated and called as such:
 * Encrypter* obj = new Encrypter(keys, values, dictionary);
 * string param_1 = obj->encrypt(word1);
 * int param_2 = obj->decrypt(word2);
 */
```

#### Go

```go
type Encrypter struct {
	mp  map[byte]string
	cnt map[string]int
}

func Constructor(keys []byte, values []string, dictionary []string) Encrypter {
	mp := map[byte]string{}
	cnt := map[string]int{}
	for i, k := range keys {
		mp[k] = values[i]
	}
	e := Encrypter{mp, cnt}
	for _, v := range dictionary {
		e.cnt[e.Encrypt(v)]++
	}
	return e
}

func (this *Encrypter) Encrypt(word1 string) string {
	var ans strings.Builder
	for _, c := range word1 {
		if v, ok := this.mp[byte(c)]; ok {
			ans.WriteString(v)
		} else {
			return ""
		}
	}
	return ans.String()
}

func (this *Encrypter) Decrypt(word2 string) int {
	return this.cnt[word2]
}

/**
 * Your Encrypter object will be instantiated and called as such:
 * obj := Constructor(keys, values, dictionary);
 * param_1 := obj.Encrypt(word1);
 * param_2 := obj.Decrypt(word2);
 */
```

#### TypeScript

```ts
class Encrypter {
    private mp: Map<string, string> = new Map();
    private cnt: Map<string, number> = new Map();

    constructor(keys: string[], values: string[], dictionary: string[]) {
        for (let i = 0; i < keys.length; i++) {
            this.mp.set(keys[i], values[i]);
        }
        for (const w of dictionary) {
            const encrypted = this.encrypt(w);
            if (encrypted !== '') {
                this.cnt.set(encrypted, (this.cnt.get(encrypted) || 0) + 1);
            }
        }
    }

    encrypt(word: string): string {
        let res = '';
        for (const c of word) {
            if (!this.mp.has(c)) {
                return '';
            }
            res += this.mp.get(c);
        }
        return res;
    }

    decrypt(word: string): number {
        return this.cnt.get(word) || 0;
    }
}

/**
 * Your Encrypter object will be instantiated and called as such:
 * const obj = new Encrypter(keys, values, dictionary);
 * const param_1 = obj.encrypt(word1);
 * const param_2 = obj.decrypt(word2);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
