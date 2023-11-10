import 'package:flutter/material.dart';
import 'package:storyly_flutter/storyly_flutter.dart';

import 'scroll_example.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'StorylyDemo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const HomePage(title: 'Storyly Demo Page'),
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({
    Key? key,
    required this.title,
  }) : super(key: key);

  final String title;

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  static const storylyInstanceToken =
      "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcxMzcsImFwcF9pZCI6MTIyNjIsImluc19pZCI6MTMyMzd9.CfmZwJaMa8QkLU1GgIpdfN2UjUpr_uTnoF1m9OuoxyY";

  late StorylyViewController storylyViewController;

  void onStorylyViewCreated(StorylyViewController storylyViewController) {
    this.storylyViewController = storylyViewController;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            Container(
              margin: const EdgeInsets.only(top: 10),
              height: 120,
              child: StorylyView(
                onStorylyViewCreated: onStorylyViewCreated,
                androidParam: StorylyParam()
                  ..storylyId = storylyInstanceToken
                  ..storyGroupListHorizontalEdgePadding = 20
                  ..storyGroupListHorizontalPaddingBetweenItems = 20,
                iosParam: StorylyParam()
                  ..storylyId = storylyInstanceToken
                  ..storyGroupListHorizontalEdgePadding = 20
                  ..storyGroupListHorizontalPaddingBetweenItems = 20,
                storylyLoaded: (storyGroups, dataSource) {
                  debugPrint(
                    "storylyLoaded -> storyGroups: ${storyGroups.length}",
                  );
                  debugPrint("storylyLoaded -> dataSource: $dataSource");
                },
                storylyLoadFailed: (errorMessage) =>
                    debugPrint("storylyLoadFailed"),
                storylyActionClicked: (story) {
                  debugPrint("storylyActionClicked -> ${story.title}");
                },
                storylyEvent: (event, storyGroup, story, storyComponent) {
                  debugPrint("storylyEvent -> event: $event");
                  debugPrint(
                    "storylyEvent -> storyGroup: ${storyGroup?.title}",
                  );
                  debugPrint("storylyEvent -> story: ${story?.title}");
                  debugPrint("storylyEvent -> storyComponent: $storyComponent");
                },
                storylyStoryShown: () => debugPrint("storylyStoryShown"),
                storylyStoryDismissed: () => debugPrint(
                  "storylyStoryDismissed",
                ),
                storylyUserInteracted: (storyGroup, story, storyComponent) {
                  debugPrint(
                    "userInteracted -> storyGroup: ${storyGroup.title}",
                  );
                  debugPrint("userInteracted -> story: ${story.title}");
                  debugPrint(
                    "userInteracted -> storyComponent: $storyComponent",
                  );
                },
              ),
            ),
            TextButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => const ScrollExample(),
                  ),
                );
              },
              child: const Text("Scroll Example"),
            ),
          ],
        ),
      ),
    );
  }
}
