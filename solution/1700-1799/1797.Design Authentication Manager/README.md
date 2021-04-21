# [1797. 设计一个验证系统](https://leetcode-cn.com/problems/design-authentication-manager)

[English Version](/solution/1700-1799/1797.Design%20Authentication%20Manager/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你需要设计一个包含验证码的验证系统。每一次验证中，用户会收到一个新的验证码，这个验证码在 <code>currentTime</code> 时刻之后 <code>timeToLive</code> 秒过期。如果验证码被更新了，那么它会在 <code>currentTime</code> （可能与之前的 <code>currentTime</code> 不同）时刻延长 <code>timeToLive</code> 秒。</p>

<p>请你实现 <code>AuthenticationManager</code> 类：</p>

<ul>
	<li><code>AuthenticationManager(int timeToLive)</code> 构造 <code>AuthenticationManager</code> 并设置 <code>timeToLive</code> 参数。</li>
	<li><code>generate(string tokenId, int currentTime)</code> 给定 <code>tokenId</code> ，在当前时间 <code>currentTime</code> 生成一个新的验证码。</li>
	<li><code>renew(string tokenId, int currentTime)</code> 将给定 <code>tokenId</code> 且 <strong>未过期</strong> 的验证码在 <code>currentTime</code> 时刻更新。如果给定 <code>tokenId</code> 对应的验证码不存在或已过期，请你忽略该操作，不会有任何更新操作发生。</li>
	<li><code>countUnexpiredTokens(int currentTime)</code> 请返回在给定 <code>currentTime</code> 时刻，<strong>未过期</strong> 的验证码数目。</li>
</ul>

<p>如果一个验证码在时刻 <code>t</code> 过期，且另一个操作恰好在时刻 <code>t</code> 发生（<code>renew</code> 或者 <code>countUnexpiredTokens</code> 操作），过期事件 <strong>优先于</strong> 其他操作。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/25/copy-of-pc68_q2.png" style="width: 500px; height: 287px;" />
<pre>
<strong>输入：</strong>
["AuthenticationManager", "<code>renew</code>", "generate", "<code>countUnexpiredTokens</code>", "generate", "<code>renew</code>", "<code>renew</code>", "<code>countUnexpiredTokens</code>"]
[[5], ["aaa", 1], ["aaa", 2], [6], ["bbb", 7], ["aaa", 8], ["bbb", 10], [15]]
<strong>输出：</strong>
[null, null, null, 1, null, null, null, 0]

<strong>解释：</strong>
AuthenticationManager authenticationManager = new AuthenticationManager(5); // 构造 AuthenticationManager ，设置 <code>timeToLive</code> = 5 秒。
authenticationManager.<code>renew</code>("aaa", 1); // 时刻 1 时，没有验证码的 tokenId 为 "aaa" ，没有验证码被更新。
authenticationManager.generate("aaa", 2); // 时刻 2 时，生成一个 tokenId 为 "aaa" 的新验证码。
authenticationManager.<code>countUnexpiredTokens</code>(6); // 时刻 6 时，只有 tokenId 为 "aaa" 的验证码未过期，所以返回 1 。
authenticationManager.generate("bbb", 7); // 时刻 7 时，生成一个 tokenId 为 "bbb" 的新验证码。
authenticationManager.<code>renew</code>("aaa", 8); // tokenId 为 "aaa" 的验证码在时刻 7 过期，且 8 >= 7 ，所以时刻 8 的renew 操作被忽略，没有验证码被更新。
authenticationManager.<code>renew</code>("bbb", 10); // tokenId 为 "bbb" 的验证码在时刻 10 没有过期，所以 renew 操作会执行，该 token 将在时刻 15 过期。
authenticationManager.<code>countUnexpiredTokens</code>(15); // tokenId 为 "bbb" 的验证码在时刻 15 过期，tokenId 为 "aaa" 的验证码在时刻 7 过期，所有验证码均已过期，所以返回 0 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= timeToLive <= 10<sup>8</sup></code></li>
	<li><code>1 <= currentTime <= 10<sup>8</sup></code></li>
	<li><code>1 <= tokenId.length <= 5</code></li>
	<li><code>tokenId</code> 只包含小写英文字母。</li>
	<li>所有 <code>generate</code> 函数的调用都会包含独一无二的 <code>tokenId</code> 值。</li>
	<li>所有函数调用中，<code>currentTime</code> 的值 <strong>严格递增</strong> 。</li>
	<li>所有函数的调用次数总共不超过 <code>2000</code> 次。</li>
</ul>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **...**

```

```

<!-- tabs:end -->
