# Workflow: tích hợp, Git và bàn giao

Áp cho output thực được yêu cầu: files, tài liệu ghép hoặc site song ngữ. Không bắt mọi project có website/build/deploy.

## 1. Release gate

Dùng snapshot target ổn định. Inventory phải đủ scope; required semantic/mechanical/boundary checks đạt; hash khớp evidence; không còn blocker; không có concurrent writer. Nếu chưa đạt vẫn có thể bàn giao draft được gắn nhãn rõ, không gọi full/final verified.

Với tài liệu liên tục, ghép theo inventory order và workflow boundary, không theo filesystem order; kiểm tra tuần tự document sau ghép. Nếu không kiểm tra toàn document, report đúng phần đã kiểm tra. Với repo docs, giữ cây file thay vì ghép thành sách một cách tự động.

## 2. Site song ngữ — chỉ khi site.enabled

Thiết lập source→target route registry từ output thật, gồm base path, slug/extension, explicit/generated anchor và asset. Giữ content source; UI/config integration chỉ sửa wrapper/overlay hoặc vùng đã được user cho phép. Không tự đổi theme hay thêm feature ngoài scope.

Các kiểm tra cần có:

- Trang nguồn và bản dịch cùng mapping mở được; direct deep link và refresh không 404.
- Chuyển ngôn ngữ giữ trang tương ứng; fragment được map khi có; trang chưa dịch dùng nút disabled hoặc link về nguồn có nhãn rõ, không âm thầm về home.
- Sidebar/previous/next/internal content link trong bản dịch giữ locale theo policy; relative link và root-relative link được xử lý khác nhau khi cần.
- Assets, includes, search/nav locale nếu trong scope; base path thực khi deploy dưới subdirectory; không giả định luôn host ở `/`.
- Build cả hai locale không ghi đè output nhau. Build order/output dir được xác minh từ tool hiện có, không copy lệnh một repo khác.
- Component render sau hydration phải kiểm tra DOM/browser thật, không kết luận từ grep HTML tĩnh. Console/network error cần ghi lại nếu runtime có công cụ.

Kiểm tra build và kiểm tra deployment là hai bước khác nhau. Không sửa một lỗi route bằng blanket redirect về home, tạo trang rỗng hoặc hạ toàn bộ link errors thành warnings. Known issue của upstream được baseline riêng, không che lỗi do bản dịch tạo.

## 3. Git — không tự cấp quyền

Mặc định không commit/push/deploy. Chỉ thực hiện khi context và yêu cầu hiện hành cho phép; ghi remote/branch cụ thể. Kiểm tra diff và stage đúng file thuộc task, không `git add .` khi có thay đổi ngoài scope. Không tự commit các file nguồn hoặc sửa của người dùng.

Review xong unit mới commit theo policy; push chỉ sau quyền push và các check cần thiết. Main không phải branch mặc định bắt buộc. Không force-push, xoá branch hoặc sửa history nếu không có uỷ quyền riêng.

Báo commit hash khi commit thành công; remote ref khi push được xác nhận; URL deployment chỉ khi thật sự có và đã kiểm chứng. Một status `verified` không tự suy ra ba kết quả này.

## 4. Package

ZIP chỉ chứa artifact theo scope, assets cần thiết và report/control files được yêu cầu; không thêm source material, cache, token, secret, node_modules hay render tạm mặc định. Giữ attribution/notice cần thiết theo nguồn và yêu cầu project; không suy ra quyền phân phối từ việc repo public.

Kiểm tra tồn tại, số file, archive CRC, đường dẫn an toàn, thiếu asset và checksum manifest khi dùng. Phân biệt số unit dịch với số file toàn archive. Liệt kê phần chưa làm và required checks chưa chạy, không thay bằng lời hứa thực hiện sau.
