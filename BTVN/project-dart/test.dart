import 'dart:io';

class Student {
  int id;
  String name;
  String phoneNumber;

  Student(this.id, this.name, this.phoneNumber);

  @override
  String toString() {
    return 'ID: $id, Name: $name, Phone Number: $phoneNumber';
  }
}

void main() {
  List<Student> students = [];
  while (true) {
    print('''
    Menu:
    1. Thêm sinh viên
    2. Hiển thị danh sách sinh viên
    3. Sửa thông tin sinh viên
    4. Xóa sinh viên
    5. Thoát
        ''');
    String? choice = stdin.readLineSync();
    switch (choice) {
      case '1':
        addStudent(students);
        break;
      case '2':
        displayStudents(students);
        break;
      case '3':
        editStudent(students);
        break;
      case '4':
        deleteStudent(students);
        break;
      case '5':
        print('Thoát chương trình');
        exit(0);
      default:
        print('Chọn sai. Vui lòng chọn lại.');
    }
  }
}

void addStudent(List<Student> students) {
  print('Nhập ID sinh viên: ');
  int? id = int.tryParse(stdin.readLineSync() ?? '');
  if (id == null) {
    print('ID không hợp lệ');
    return;
  }
  print('Nhập tên sinh viên: ');
  String? name = stdin.readLineSync();
  if (name == null || name.isEmpty) {
    print('Tên không hợp lệ');
    return;
  }
  print('Nhập số điện thoại: ');
  String? phoneNumber = stdin.readLineSync();
  if (phoneNumber == null || phoneNumber.isEmpty) {
    print('Số điện thoại không hợp lệ');
    return;
  }
  students.add(Student(id, name, phoneNumber));
  print('Sinh viên đã được thêm.');
}

void displayStudents(List<Student> students) {
  if (students.isEmpty) {
    print('Danh sách sinh viên trống.');
  } else {
    print('Danh sách sinh viên: ');
    for (var student in students) {
      print(student);
    }
  }
}

void editStudent(List<Student> students) {
  print('Nhập ID của sinh viên cần sửa: ');
  int? id = int.tryParse(stdin.readLineSync() ?? '');
  if (id == null) {
    print('ID không hợp lệ');
    return;
  }
  var student = students.firstWhere(
        (student) => student.id == id,
    orElse: () => Student(-1, '', ''),
  );
  if (student.id == -1) {
    print('Không tìm thấy sinh viên với ID đã nhập.');
    return;
  }
  print('Nhập tên mới (để trống nếu không muốn thay đổi): ');
  String? newName = stdin.readLineSync();
  if (newName != null && newName.isNotEmpty) {
    student.name = newName;
  }
  print('Nhập số điện thoại mới (để trống nếu không muốn thay đổi): ');
  String? newPhoneNumber = stdin.readLineSync();
  if (newPhoneNumber != null && newPhoneNumber.isNotEmpty) {
    student.phoneNumber = newPhoneNumber;
  }
  print('Thông tin sinh viên đã được cập nhật.');
}

void deleteStudent(List<Student> students) {
  print('Nhập ID của sinh viên cần xóa: ');
  int? id = int.tryParse(stdin.readLineSync() ?? '');
  if (id == null) {
    print('ID không hợp lệ');
    return;
  }
  students.removeWhere((student) => student.id == id);
  print('Sinh viên đã được xóa.');
}
