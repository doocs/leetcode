# Project Rules — LeetCode Vietnamese Mirror

## Scope và mapping

Source:

- `solution/*/*/README_EN.md`
- `lcci/*/README_EN.md`

Target:

- `vi/solution/*/*/README.md`
- `vi/lcci/*/README.md`

Không sửa source upstream trong `solution/`, `lcci/`, `lcof/`, `lcof2/`, `lcp/`, `lcs/`.

## Tên bài

Giữ nguyên H1 và tên bài chính thức bằng English.

```md
# [1. Two Sum](https://leetcode.com/problems/two-sum)
```

## Front matter, code và literal

Giữ nguyên:

- front matter;
- code fence và code;
- code comments;
- inline code;
- LaTeX;
- URL;
- HTML comment marker;
- input/output literal;
- code-tab heading.

## Nhãn cấu trúc

| Source | Target |
| --- | --- |
| `## Description` | `## Mô tả` |
| `## Solutions` | `## Lời giải` |
| `### Solution N: ...` | `### Lời giải N: ...` |
| `> **Thinking**` | `> **Tư duy**` |
| `Example N:` | `Ví dụ N:` |
| `Input:` | `Đầu vào:` |
| `Output:` | `Đầu ra:` |
| `Explanation:` | `Giải thích:` |
| `Constraints:` | `Ràng buộc:` |
| `Note:` | `Lưu ý:` |
| `Follow-up:` | `Câu hỏi mở rộng:` |

Language tab như `#### Python3`, `#### Java`, `#### Go` giữ nguyên.

## Link

Giữ nguyên URL trong source. Không tự rewrite link trong file dịch; site layer chịu trách nhiệm route mapping.

## Style thuật toán

Không ép toàn bộ term thuật toán sang tiếng Việt.

- `array` thường dùng “mảng”;
- `string` thường dùng “chuỗi”;
- `index` trong array thường dùng “chỉ số”;
- `node`, `stack`, `queue`, `heap`, `hash map`, `hash set` có thể giữ English nếu câu tự nhiên hơn;
- tên class, method, type trong code luôn giữ nguyên.

Glossary là guideline, không phải bảng replace bắt buộc.

## Publish

Nếu target `vi/**/README.md` tồn tại và CI mechanical checks pass, site có thể publish trực tiếp.

Không yêu cầu unit report, verified state hoặc hash file để publish.
