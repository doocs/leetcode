# Đối chiếu ba bộ rule nguồn và thiết kế hợp nhất

Ngày đọc: **01/10/2026**. Phạm vi: **34 file rule/instruction/prompt/glossary liên quan**, không phải toàn bộ nội dung ba repo hay toàn bộ sách. Các file được đọc qua GitHub connector; không sửa repository.

Danh sách đầy đủ và Git tree snapshot quan sát được nằm trong [source-inventory.json](source-inventory.json). Tree SHA được ghi đúng là **tree SHA, không phải commit SHA**. Phần lớn nội dung file lấy qua ref `main` trong phiên đọc; inventory không giả rằng từng response đều được tải bằng commit-pinned URL. Không kèm sách/PDF gốc vào gói.

## Nguồn tham chiếu chính

- [Learn PostgreSQL — core](https://github.com/vandunxg/learn-postgresql/blob/main/translation/instructions/00-core-rules.md), [style](https://github.com/vandunxg/learn-postgresql/blob/main/translation/instructions/01-translation-style.md), [SQL/code/output](https://github.com/vandunxg/learn-postgresql/blob/main/translation/instructions/06-sql-code-output-fidelity.md), [semantic review](https://github.com/vandunxg/learn-postgresql/blob/main/translation/instructions/08-semantic-review.md), [QA](https://github.com/vandunxg/learn-postgresql/blob/main/translation/instructions/09-qa-validation.md).
- [Effective Java — core](https://github.com/vandunxg/effective-java/blob/main/instructions/00-core-rules.md), [boundary](https://github.com/vandunxg/effective-java/blob/main/instructions/04-boundary-rules.md), [Markdown/code](https://github.com/vandunxg/effective-java/blob/main/instructions/05-markdown-preservation.md), [merge](https://github.com/vandunxg/effective-java/blob/main/instructions/08-merge-and-package.md).
- [JavaGuide — CLAUDE.md](https://github.com/vandunxg/JavaGuide/blob/main/CLAUDE.md), [glossary](https://github.com/vandunxg/JavaGuide/blob/main/vi/GLOSSARY.md).

## Điểm chung được giữ

Cả ba hướng tới bản dịch đầy đủ, trung thành với nguồn, dùng tiếng Việt tự nhiên và giữ English technical terms theo context. Hai bộ sách có workflow boundary/ownership/merge rõ; Learn PostgreSQL nhấn mạnh semantic review và structured output; JavaGuide bổ sung kinh nghiệm mirror docs, frontmatter, site hai locale và upstream sync.

Base giữ các giá trị này nhưng tách **source kind**, **target format**, **domain** và **execution/release** thành bốn trục. Một repo Markdown về PostgreSQL có thể dùng repo adapter + Markdown format + PostgreSQL domain mà không cần PDF workflow. Một sách Java từ PDF không phải nạp VuePress/Vercel.

## Xung đột và điểm cần chỉnh

| Điểm trong bộ nguồn                                                                                                                        | Rủi ro khi copy nguyên                                                            | Quyết định trong base                                                                                                   |
| ------------------------------------------------------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- |
| JavaGuide yêu cầu dịch mọi chữ Hán, kể cả một số thành phần trong code; đồng thời có quy tắc giữ identifier/code/output và một số ngoại lệ | Đổi token kỹ thuật, input chính xác hoặc string có semantics chỉ để scan sạch     | CODE-01..05: preserve mặc định; comment và display string có policy/allowlist riêng; QA-06 phân loại hit                |
| Effective Java và Learn PostgreSQL giữ comment trong code; JavaGuide dịch comment/chuỗi tiếng Trung                                        | Một prompt không thể vừa “bất biến toàn block” vừa dịch mọi comment/string        | Tách `code_comments` khỏi `display_strings`; ghi policy hiệu lực trong context; không ngầm kế thừa theo ngôn ngữ nguồn  |
| JavaGuide có giới hạn độ dài bản dịch so với ý/câu gốc                                                                                     | Ép ngắn gây mất điều kiện, nuance hoặc quan hệ                                    | CORE-08: súc tích nhưng không có hard word/character limit giữa hai ngôn ngữ                                            |
| Một phần JavaGuide cho rằng link nội bộ giữ nguyên vì thư mục mirror                                                                       | Root-relative link, anchor tự sinh, base path và include có thể sai               | STRUCT-03 và release: giữ đích semantic bằng mapping/renderer checks, không giữ string đường dẫn một cách mù quáng      |
| JavaGuide có hướng dẫn CodeGraph cho đọc docs, rồi giải thích công cụ đó không index Markdown                                              | Gắn workflow với một công cụ không có hoặc không phù hợp                          | Chỉ yêu cầu capability/phương pháp kiểm chứng được; không bắt tên tool/vendor                                           |
| JavaGuide có đoạn nói `vi/` không chứa `.vuepress/`, đoạn sau lại triển khai site riêng dưới đó                                            | Rule cũ và mới cùng hiệu lực, agent không biết scope                              | Entry point canonical, context kích hoạt module, migration decision thay thế rule cũ; không ưu tiên theo thứ tự tình cờ |
| JavaGuide vừa bảo vệ `package.json`, vừa có các lệnh/luồng site cũ-mới khác nhau                                                           | Sửa upstream hoặc chạy lệnh không còn đúng                                        | Bootstrap khảo sát command thực; wrapper/overlay và quyền integration riêng                                             |
| JavaGuide nêu kiến trúc không bao giờ conflict với upstream                                                                                | Đảm bảo tuyệt đối không thể suy ra từ việc tách thư mục hiện tại                  | Chỉ cam kết thiết kế giảm conflict; kiểm tra collision và diff source mỗi lần sync                                      |
| Các bộ cũ có hardcode tên sách, edition, số trang/part và giới hạn worker                                                                  | Context rò sang project khác; tốn context hoặc bỏ nội dung                        | Context/domain/source adapter riêng, một worker mặc định; sizing theo nguồn thật và capability                          |
| Effective Java dùng metadata trong target và boundary reviewer cập nhật report chung                                                       | Metadata lọt vào sách; reviewer cặp không overlap vẫn có thể ghi đè shared report | Sidecar riêng từng unit/boundary; coordinator là writer duy nhất cho state tổng                                         |
| Boundary review hai wave là điểm mạnh của Learn PostgreSQL                                                                                 | Chỉ khoá cặp không đủ cho construct kéo dài nhiều part                            | Giữ hai wave cho pair; bổ sung ownership cả interval, evidence invalidation sau sửa                                     |
| Một số QA/DoD nói lỗi nặng đã resolve “hoặc report rõ”; có trạng thái pass kèm boundary chưa review                                        | Report lỗi bị hiểu thành đủ điều kiện final                                       | QA-04..05: unresolved critical/high chặn verified; partial/draft là nhãn riêng, không phải PASS                         |
| JavaGuide dùng scan chữ Hán; các bộ sách kiểm tra completeness/semantic                                                                    | Scan rỗng, output tồn tại hoặc build pass dễ bị hiểu thành dịch đúng              | Coverage hai chiều + full source-vs-target + mechanical checks tách biệt                                                |
| Progress/sync có thể dùng trạng thái tổng hoặc global mốc sync                                                                             | Dịch một phần rồi đánh toàn corpus đã theo nguồn mới                              | State gắn source/target/policy hash từng unit và dependency; stale rõ ràng                                              |
| Các prompt cũ yêu cầu đọc nhiều file/prompt; đường dẫn có thể khác sau di chuyển thư mục                                                   | Context phình, relative path sai, domain không liên quan bị nạp                   | Read set kích hoạt theo role/context; đường dẫn từ root; examples/audit không phải instruction tự động                  |
| Quyền commit không đồng nhất giữa workflow và ví dụ deploy                                                                                 | “Dịch” bị hiểu thành được commit/push main/deploy                                 | Ba quyền riêng, mặc định false; stage đúng file, evidence từng thao tác                                                 |

Các nhận xét rủi ro ở bảng là **đánh giá thiết kế của gói này**, không phải kết luận rằng repo hiện tại đã phát sinh mọi lỗi nêu trên. Các đoạn lịch sử trong instruction không được dùng để khẳng định trạng thái runtime hiện tại của site.

## Những phần bổ sung mới

Source snapshot và type rõ; source/target coverage hai chiều; read set tối thiểu theo module; source text không được coi là command/instruction cho agent; assignment retry có hash/attempt; reviewer mode trung thực; phục hồi sau compaction bằng raw source; dependency-aware stale detection; gate release không bị nới bằng report/placeholder; regression cases riêng để đánh giá hành vi agent.

## Cách dùng ba cấu hình mẫu

[JavaGuide](../examples/javaguide.context.yaml): repo-docs, Markdown, Java, mirror docs; policy comment/string cần được chủ project chốt lại vì khác biệt với bộ cũ. [Learn PostgreSQL](../examples/learn-postgresql.context.yaml): PDF adapter, Markdown, PostgreSQL; đường dẫn PDF thực phải được khảo sát. [Effective Java](../examples/effective-java.context.yaml): PDF parts, Markdown, Java; giữ edition theo tài liệu nguồn đã đọc, nhưng vẫn phải xác minh bản PDF được giao.

Mọi mẫu đều `needs_setup`, không chứa hash/bằng chứng review giả và không tự cấp quyền push. Dùng chúng như điểm bắt đầu cho bootstrap, không đè thẳng lên project đang có rule.

## Giới hạn kiểm chứng

Gói được kiểm tra tĩnh và kiểm tra archive theo [PACKAGE_VALIDATION.md](../../PACKAGE_VALIDATION.md). Bộ regression mô tả input/expected behavior, chưa phải benchmark thực nghiệm trên nhiều model. Prompt không thay thế sandbox permissions, lock/runtime coordination, parser, browser hoặc review con người.
