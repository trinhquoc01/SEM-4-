import 'dart:convert';
import 'dart:io';

// Định nghĩa lớp Subject
class Subject {
  String name;
  List<int> scores;

  Subject({required this.name, required this.scores});

  factory Subject.fromJson(Map<String, dynamic> json) {
    return Subject(
      name: json['name'],
      scores: List<int>.from(json['scores']),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'name': name,
      'scores': scores,
    };
  }
}

// Định nghĩa lớp Student
class Student {
  int id;
  String name;
  List<Subject> subjects;

  Student({required this.id, required this.name, required this.subjects});

  factory Student.fromJson(Map<String, dynamic> json) {
    var list = json['subjects'] as List;
    List<Subject> subjectList = list.map((i) => Subject.fromJson(i)).toList();

    return Student(
      id: json['id'],
      name: json['name'],
      subjects: subjectList,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'subjects': subjects.map((e) => e.toJson()).toList(),
    };
  }
}

// Hàm load dữ liệu từ file JSON
List<Student> loadStudents(String fileName) {
  final file = File(fileName);
  final jsonData = jsonDecode(file.readAsStringSync())['students'] as List;
  return jsonData.map((e) => Student.fromJson(e)).toList();
}

// Hàm lưu dữ liệu vào file JSON
void saveStudents(List<Student> students, String fileName) {
  final file = File(fileName);
  final jsonString =
  jsonEncode({'students': students.map((e) => e.toJson()).toList()});
  file.writeAsStringSync(jsonString);
}

// Hiển thị toàn bộ sinh viên
void displayAllStudents(List<Student> students) {
  for (var student in students) {
    print('ID: ${student.id}, Name: ${student.name}');
    for (var subject in student.subjects) {
      print('  Subject: ${subject.name}, Scores: ${subject.scores}');
    }
  }
}

// Thêm sinh viên mới
void addStudent(List<Student> students, Student newStudent) {
  students.add(newStudent);
}

// Chỉnh sửa thông tin sinh viên
void editStudent(List<Student> students, int id,
    {String? newName, List<Subject>? newSubjects}) {
  var student = students.firstWhere((student) => student.id == id,
      orElse: () => throw 'Student not found');
  if (newName != null) {
    student.name = newName;
  }
  if (newSubjects != null) {
    student.subjects = newSubjects;
  }
}

// Tìm kiếm sinh viên theo tên hoặc ID
Student? searchStudent(List<Student> students, {String? name, int? id}) {
  return students.firstWhere(
        (student) =>
    (name != null && student.name == name) ||
        (id != null && student.id == id),
    orElse: () => throw 'Student not found',
  );
}

// Hiển thị sinh viên có điểm cao nhất trong môn học
void displayTopScorers(List<Student> students, String subjectName) {
  int highestScore = 0;
  for (var student in students) {
    for (var subject in student.subjects) {
      if (subject.name == subjectName) {
        highestScore = highestScore <
            subject.scores.reduce((a, b) => a > b ? a : b)
            ? subject.scores.reduce((a, b) => a > b ? a : b)
            : highestScore;
      }
    }
  }
  print('Students with the highest score of $highestScore in $subjectName:');
  for (var student in students) {
    for (var subject in student.subjects) {
      if (subject.name == subjectName &&
          subject.scores.contains(highestScore)) {
        print('${student.name}');
      }
    }
  }
}

// Hàm chính
void main() {
  List<Student> students = loadStudents('Student.json');

  displayAllStudents(students); // Hiển thị toàn bộ sinh viên

  // Thêm một sinh viên mới
  addStudent(students,
      Student(id: 3, name: 'Nguyen Van A', subjects: [
        Subject(name: 'Math', scores: [7, 8, 9]),
        Subject(name: 'Physics', scores: [6, 8, 7])
      ]));

  // Chỉnh sửa thông tin sinh viên
  editStudent(students, 1, newName: 'Tran Van B', newSubjects: [
    Subject(name: 'Math', scores: [8, 9, 10]),
    Subject(name: 'Physics', scores: [9, 9, 9])
  ]);

  // Tìm kiếm sinh viên
  try {
    var student = searchStudent(students, id: 1);
    print('Found student: ${student!.name}');
  } catch (e) {
    print(e);
  }

  // Hiển thị sinh viên có điểm cao nhất trong môn Math
  displayTopScorers(students, 'Math');

  // Lưu lại các thay đổi
  saveStudents(students, 'Student.json');
}
