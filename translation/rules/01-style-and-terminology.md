# Văn phong và terminology

## STYLE-01 — Tiếng Việt tự nhiên, English-first theo meaning

Dịch grammar, liên từ và giải thích thông thường thành tiếng Việt rõ, trực tiếp. Giữ English cho technical/domain concept khi cách đó chính xác, phổ biến hoặc nhất quán với model/API của tài liệu. Không ép Việt hoá từng token, cũng không biến cả câu thành tiếng Anh xen vài từ Việt.

“request” là HTTP object có thể giữ `request`; “request access” là hành động thông thường thường dịch “yêu cầu quyền truy cập”. “table” trong database và “table of contents” không phải một nghĩa. Không global-replace theo glossary.

## STYLE-02 — Glossary theo concept và scope

Glossary là baseline, không phải whitelist. Term mới vẫn có thể giữ English nếu đúng context. Với mỗi entry nên có source term, concept/scope, preferred rendering, biến thể không dùng, ví dụ nguồn và lý do. Một concept dùng nhất quán; cùng một token mang nghĩa khác được phép có rendering khác.

Entry được duyệt của project cụ thể hoá domain baseline, nhưng không được đổi nghĩa nguồn. Domain profile không áp đặt kiến thức của ngành này sang ngành khác. Trong parallel batch glossary read-only; worker ghi term proposal riêng, coordinator duyệt rồi tăng revision.

## STYLE-03 — Không tự thêm diễn giải

Không tự gắn “tiếng Việt (English)” sau mỗi term hoặc thêm định nghĩa ở lần đầu. Chỉ dịch phần giải thích mà source thực sự có. Nếu người dùng yêu cầu glossary học tập, tạo artifact riêng, không nhét vào bản dịch.

Giữ ngôi xưng, câu hỏi, giọng cảnh báo, tính khuyến nghị và mức trang trọng có nghĩa trong source. Không thêm ngôi “chúng ta”, lời chào, emoji, marketing hoặc “ví dụ dễ hiểu hơn”. Tên riêng và nhãn định danh chính thức xử lý theo project policy; không dịch tên package, API hay product như prose.

## STYLE-04 — Modal, lượng từ và phủ định

`must` thường là “phải”; `should` thường là “nên”; `may` có thể chỉ khả năng hoặc sự cho phép, phải đọc context. Không map máy móc một từ một nghĩa. `must not` khác `need not`; `not all` khác `none`; `unless` không được bỏ.

Giữ các qualifier như generally, usually, sometimes, only, at least, at most, exactly, all, some, before, after. Không biến “may improve” thành “sẽ cải thiện” hoặc “not necessarily” thành “không”. Trường hợp source dùng modal theo quy ước đặc thù phải ghi trong PROJECT_RULES, không tự suy diễn.

## STYLE-05 — Ví dụ review

| Source                                   | Đúng hướng                               | Không chấp nhận               |
| ---------------------------------------- | ---------------------------------------- | ----------------------------- |
| You should usually avoid this approach.  | Bạn thường nên tránh cách này.           | Bạn không được dùng cách này. |
| The operation is not necessarily atomic. | Thao tác này không nhất thiết là atomic. | Thao tác này không atomic.    |
| Only one request may hold the lock.      | Chỉ một request được phép giữ lock.      | Một request có thể giữ lock.  |

Ví dụ trong rule dùng để minh hoạ contract, không phải nội dung được tự chèn vào target.
