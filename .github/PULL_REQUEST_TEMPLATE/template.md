<!--
🤭 感谢你的提交，请检查你的改动是否符合以下项目规范。
-->

[English Template](?quick_pull=1&template=template_en.md)

### 1. 格式化

我们项目中各种编程语言代码（包括文档）所采用的格式化工具不同，提交 pr 之前必须确保代码、文档正确格式化。

-   .{md,js,ts,php,sql} 采用 prettier
-   .{c,cpp,java} 采用 clang-format
-   .{py} 采用 black
-   .{go} 采用 gofmt
-   其它待完善

### 2. commit msg

我们项目遵循 [AngularJS Git Commit Message Conventions](https://docs.google.com/document/d/1QrDFcIiPjSLDn3EL15IJygNPiHORgU1_OOAqWjiDU5Y/edit#) 规范，我们希望你的提交信息尽可能与项目保持一致。

-   新增或修改题解：feat: add/update solution(s) to lc problem(s): No.xxxx
-   修复错误：fix: xxxx
-   日常维护：chore: xxx

### 3. 其它补充

新增题解及代码时，需要创建 Solution.xxx 源代码文件（如果已存在，请确认算法是否更优，是则覆盖已有算法代码），同时，需要在 README.md 以及 README_EN.md 中添加对应的代码片段（英文文件中不要出现中文注释）

另外，编码风格（比如变量、函数的命名），尽量跟项目已有代码保持一致。