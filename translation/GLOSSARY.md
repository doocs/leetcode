# Glossary theo concept và context

Version: `0.2.0` (01/10/2026). Revision này thêm baseline cho domain thuật toán (`translation/domains/algorithms.md`) và các nhãn cấu trúc của PROJECT-003; thuật ngữ mới phát sinh khi dịch được đề xuất qua unit report.

Đọc STYLE-02 trước khi dùng. Glossary là baseline về thuật ngữ, không phải danh sách duy nhất được giữ English. Trong batch, glossary là read-only; worker ghi đề xuất vào report, coordinator duyệt ở checkpoint.

| Source term                        | Context/concept                              | Cách dùng ưu tiên                              | Không áp dụng khi                              | Trạng thái |
| ---------------------------------- | -------------------------------------------- | ---------------------------------------------- | ---------------------------------------------- | ---------- |
| API                                | Tên khái niệm hoặc chữ viết tắt trong source | API                                            | Không tự thêm chữ viết tắt nếu source không có | baseline   |
| request                            | HTTP/domain object                           | request                                        | Động từ thông thường như request access        | baseline   |
| source                             | Nguồn tài liệu đang dịch                     | nguồn / bản gốc, nhất quán theo câu            | Identifier hoặc tên chính thức                 | baseline   |
| array                              | Cấu trúc dữ liệu                             | mảng                                           | Tên kiểu trong code (`Array`, `int[]`)         | baseline   |
| string                             | Chuỗi ký tự                                  | chuỗi                                          | Tên kiểu trong code (`String`, `str`)          | baseline   |
| matrix                             | Mảng hai chiều                               | ma trận                                        | Tag `Matrix` trong front matter                | baseline   |
| row / column                       | Ma trận                                      | hàng / cột                                     | —                                              | baseline   |
| element                            | Phần tử của mảng/ma trận                     | phần tử                                        | —                                              | baseline   |
| index / indices                    | Vị trí trong mảng                            | chỉ số                                         | Database index                                 | baseline   |
| hash table                         | Cấu trúc dữ liệu                             | bảng băm                                       | Tag `Hash Table` trong front matter            | baseline   |
| hash set                           | Tập hợp dựa trên băm                         | hash set                                       | —                                              | baseline   |
| binary search                      | Kỹ thuật                                     | tìm kiếm nhị phân                              | Tag `Binary Search`                            | baseline   |
| bit manipulation                   | Kỹ thuật                                     | thao tác bit                                   | Tag `Bit Manipulation`                         | baseline   |
| mask                               | Số nguyên dùng làm tập bit                   | mask                                           | Biến `mask` trong code giữ nguyên              | baseline   |
| pointer                            | Biến chỉ vị trí khi duyệt                    | con trỏ                                        | —                                              | baseline   |
| traverse / walk                    | Duyệt qua mảng/cấu trúc                      | duyệt                                          | —                                              | baseline   |
| sorted / non-decreasing order      | Thứ tự                                       | đã sắp xếp / thứ tự không giảm                 | —                                              | baseline   |
| complement                         | Giá trị bù trong Two Sum ($target - x$)      | phần bù                                        | —                                              | baseline   |
| time complexity / space complexity | Phân tích thuật toán                         | độ phức tạp thời gian / độ phức tạp không gian | —                                              | baseline   |
| expected $O(1)$                    | Độ phức tạp kỳ vọng                          | $O(1)$ kỳ vọng                                 | —                                              | baseline   |
| follow-up                          | Câu hỏi bổ sung cuối đề                      | câu hỏi mở rộng                                | —                                              | baseline   |
| subarray / subsequence / substring | Bẫy nghĩa                                    | mảng con / dãy con / chuỗi con                 | —                                              | baseline   |

Đây là bảng khởi đầu, không phải glossary đầy đủ. Nhãn cấu trúc cố định (Description, Solutions, Example, Input, Output, Constraints, …) nằm ở PROJECT-003.

## Đề xuất term mới

Ghi source term, một đoạn context ngắn, source locator, cách dùng đề xuất, các trường hợp dễ nhầm và unit bị ảnh hưởng. Không global-replace toàn repo. Không thêm định nghĩa hoặc bản dịch ngoặc vào target chỉ vì glossary có phần giải thích dành cho agent.

Term nhiều nghĩa được phép có nhiều hàng nếu scope khác nhau. Một concept trong cùng scope không đổi qua lại theo sở thích. Khi hai domain dùng khác nhau, ghi quyết định cho scope cụ thể thay vì lấy thứ tự file làm ưu tiên ngầm.
