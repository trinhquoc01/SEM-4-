import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'YouTube Search',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: SearchScreen(),
    );
  }
}

class SearchScreen extends StatefulWidget {
  @override
  _SearchScreenState createState() => _SearchScreenState();
}

class _SearchScreenState extends State<SearchScreen> {
  final TextEditingController _controller = TextEditingController();
  final YoutubeApi _youtubeApi = YoutubeApi('AIzaSyDuJ5CkPRS-5uNZXvAwN_QTFgexuyioR5U');
  List<dynamic> _videos = [];

  void _search() async {
    final videos = await _youtubeApi.searchVideos(_controller.text);
    setState(() {
      _videos = videos;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('YouTube Search'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _controller,
              decoration: InputDecoration(
                labelText: 'Search',
                suffixIcon: IconButton(
                  icon: Icon(Icons.search),
                  onPressed: _search,
                ),
              ),
            ),
            SizedBox(height: 16),
            Expanded(
              child: ListView.builder(
                itemCount: _videos.length,
                itemBuilder: (context, index) {
                  final video = _videos[index];
                  final title = video['snippet']['title'];
                  final thumbnail = video['snippet']['thumbnails']['default']['url'];

                  return ListTile(
                    leading: Image.network(thumbnail),
                    title: Text(title),
                    onTap: () {
                      // Xử lý khi nhấn vào video (ví dụ, mở video trong trình duyệt)
                    },
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class YoutubeApi {
  final String apiKey;

  YoutubeApi(this.apiKey);

  Future<List<dynamic>> searchVideos(String query) async {
    final url = Uri.parse(
      'https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&q=$query&key=$apiKey',
    );

    final response = await http.get(url);

    if (response.statusCode == 200) {
      final data = json.decode(response.body);
      return data['items'];
    } else {
      throw Exception('Failed to fetch videos');
    }
  }
}
