# Translation Rules — Fast, Accurate, Natural

## Mục tiêu

Bản dịch phải **nhanh, chuẩn, đúng và hay**: giữ đầy đủ meaning của source, đọc tự nhiên với developer Việt Nam, không dịch word-by-word và không biến việc dịch docs thành một quy trình audit nặng nề.

Ưu tiên theo thứ tự:

1. đúng meaning và technical intent;
2. không làm mất condition, negation, scope, số liệu, complexity hoặc quan hệ logic;
3. câu tiếng Việt tự nhiên, ngắn gọn, dễ đọc;
4. dùng technical term theo cách developer thực tế sử dụng;
5. giữ cấu trúc và phần kỹ thuật không được phép sửa.

## Source of truth

- Source của mỗi bài là `README_EN.md` tương ứng.
- Dịch nội dung source hiện có, không dịch từ trí nhớ hoặc bản trên Internet.
- Không tự sửa thuật toán, claim, ví dụ hoặc complexity vì nghĩ source sai.

## Dịch meaning, không dịch từng từ

Không yêu cầu mọi từ English phải thành tiếng Việt.

Có thể và nên giữ English technical/domain term khi term đó phổ biến hơn, bản dịch tiếng Việt làm câu gượng hoặc kém chính xác, hoặc đó là tên concept/operation/component/domain concept.

Ví dụ có thể giữ: `request`, `response`, `backend`, `frontend`, `module`, `service`, `cache`, `query`, `index`, `lock`, `transaction`, `rollback`, `commit`, `batch`, `async`, `message`, `event`, `upstream`, `downstream`, `node`, `stack`, `queue`, `heap`, `hash map`, `hash set`.

Không giữ English một cách máy móc. Nếu tiếng Việt tự nhiên và chính xác hơn thì dùng tiếng Việt, ví dụ `array → mảng`, `string → chuỗi`, `time complexity → độ phức tạp thời gian`.

**Consistency theo concept và context, không phải global word mapping.**

## Style mong muốn

Style tham chiếu:

> System chủ yếu phục vụ nhân viên procurement và finance, phụ trách tra cứu sản phẩm, tạo order, approval, đồng bộ trạng thái thanh toán và tra cứu fulfillment. Backend được tách thành các module order, inventory và approval; khi tạo order, trước tiên sẽ validate request và price, sau đó tạo order và reserve inventory. Sau khi thành công, system gửi message để downstream hoàn thành các task async như thông báo approval.

Đây là style hợp lệ: câu vẫn là tiếng Việt tự nhiên nhưng không cố Việt hóa các term làm mất chất technical/domain.

Tránh:
- dịch word-by-word;
- câu văn dịch máy, quá trang trọng hoặc dài dòng;
- thêm từ chỉ để câu “thuần Việt”;
- giữ quá nhiều English đến mức câu mất ngữ pháp tiếng Việt;
- đổi meaning chỉ để câu nghe hay hơn.

## Không thêm, không bớt meaning

Phải giữ condition, exception, negation, cause/effect, thứ tự thao tác, số liệu, input/output, complexity, requirement/recommendation và mức độ chắc chắn.

Không được tóm tắt, bỏ ví dụ, thêm tutorial/FAQ, thêm algorithm khác, modernize code/API hoặc tự suy diễn.

Có thể chia hoặc gộp câu để tiếng Việt tự nhiên nếu meaning không thay đổi.

## Phần phải giữ nguyên

Không sửa:
- front matter;
- H1 tên bài chính thức;
- code fence và code;
- identifier;
- inline code;
- LaTeX/math;
- URL;
- HTML comment marker;
- dữ liệu input/output của ví dụ;
- tên language tab như `Python3`, `Java`, `Go`.

Comment bên trong code mặc định giữ nguyên.

## Quality bar

Trước khi hoàn tất một file:
1. đọc lại source và target một lượt;
2. chắc chắn không thiếu ý quan trọng;
3. kiểm tra technical term và câu khó;
4. chạy mechanical check của repo.

Không cần tạo bằng chứng cho từng bước.

## Không làm mặc định

Tác vụ dịch bình thường không cần:
- unit report;
- source/target hash;
- inventory state;
- coverage map;
- lifecycle `pending → translating → reviewing → verified`;
- reviewer/coordinator/integrator ceremony;
- audit evidence;
- boundary report;
- progress YAML;
- full semantic audit sau mỗi lần format.

Chỉ review sâu khi có ambiguity, lỗi hoặc người dùng yêu cầu.

## Definition of done

Một file hoàn tất khi meaning đầy đủ và đúng, tiếng Việt tự nhiên, technical terms hợp context, protected content không đổi, Markdown/HTML không vỡ và các check cần thiết pass.
