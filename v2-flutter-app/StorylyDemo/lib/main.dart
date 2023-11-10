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
      "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjUwMjMsImFwcF9pZCI6MTUwMDcsImluc19pZCI6MTYzOTR9.WGy07rPghlj5XN3EW7Z0lrkjYKbQYPTafLDB6k7xSbQ";

  //myApp token - Classical Token
  static const storylyToken =
      "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjc5MjQsImFwcF9pZCI6MTU0MTYsImluc19pZCI6MTY4ODZ9.djck1SGjzhm5DoevmaBy989qIasJ4mCGIZiops1Lh8U";

  late StorylyViewController storylyViewController;

  void onStorylyViewCreated(StorylyViewController storylyViewController) {
    this.storylyViewController = storylyViewController;
    //this.storylyViewController.hydrateProducts(products);
    // If you want to hydrate your products, you can use hydrateProducts method when isProductFallbackEnabled set as false
  }


  final products = [
    {
      "productId": "1",
      "productGroupId": "1",
      "title": "High-waist midi skirt",
      "url": "https://www.storyly.io/",
      "desc": "High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
      "price": 25.99,

      "currency": "USD",
      "imageUrls": ["https://random-feed-generator.vercel.app/images/clothes/group-1/1-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/2-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/3-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/4-6D7868.jpg"],
      "variants": [
        {"name":"color","value":"#6D7868"},
        {"name":"size","value":"XS"}
      ]
    },
    {
      "productId": "3",
      "productGroupId": "1",
      "title": "High-waist midi skirt",
      "url": "https://www.storyly.io/",
      "desc": "High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
      "price": 25.99,

      "currency": "USD",
      "imageUrls": ["https://random-feed-generator.vercel.app/images/clothes/group-1/1-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/2-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/3-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/4-6D7868.jpg"],
      "variants": [
        {"name":"color","value":"#6D7868"},
        {"name":"size","value":"M"}
      ]
    }
  ];


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
                storylyProductEvent: (event) {
                  if (event == "StoryCartViewClicked") {
                    debugPrint("StoryCartViewClicked -> event: $event");
                  }
                  if (event == "StoryProductSelected") {
                    debugPrint("StoryProductSelected -> event: $event");
                  }
                },
                storylyOnProductHydration: (products){
                  debugPrint("products -> products Ids: $products");
                },
                storylyOnProductCartUpdated: (event, cart, change, responseId) {
                  storylyViewController.approveCartChange(responseId, {
                    "items": [
                      {
                        "item": {
                          "productId": "1",
                          "productGroupId": "1",
                          "title": "High-waist midi skirt",
                          "url": "https://www.storyly.io/",
                          "desc": "High-waist midi skirt made of a viscose blend. Featuring a slit at the hem and invisible zip fastening.",
                          "price": 25.99,
                          "imageUrls": ["https://random-feed-generator.vercel.app/images/clothes/group-1/1-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/2-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/3-6D7868.jpg","https://random-feed-generator.vercel.app/images/clothes/group-1/4-6D7868.jpg"],
                          "variants": [
                            {"name":"color","value":"#6D7868"},
                            {"name":"size","value":"XS"}
                          ]
                        },
                        "totalPrice": 12,
                        "oldTotalPrice": 15,
                        "quantity": 2
                      }
                    ],
                    "totalPrice": 12,
                    "oldTotalPrice": 15,
                    "currency": "USD"
                  });
                },
                androidParam: StorylyParam()
                  ..storylyId = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjEwOTU1LCJhcHBfaWQiOjE2NTA4LCJpbnNfaWQiOjE4MTg2fQ.KsZUkHTGncXsXKcczSt-j2on82hl9kmrSu2PVMywJC0"
                  ..storyGroupTextTypeface= 'Lobster1.4.otf'
                //..storyInteractiveTextTypeface= 'Lobster1.4.otf'
                  ..isProductCartEnabled = true
                  ..isProductFallbackEnabled = true
                  ..storylyLayoutDirection = "rtl"
                  ..storylySegments = ['arabic', 'shoppable', "english"]
                  ..storyGroupListHorizontalEdgePadding = 20
                  ..storyGroupListHorizontalPaddingBetweenItems = 20,
                iosParam: StorylyParam()
                  ..storylyId = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjEwOTU1LCJhcHBfaWQiOjE2NzE5LCJpbnNfaWQiOjE4NDU5fQ.-htu_LzSm4u0INmLUQqtqVP9jT1NS58mu1mhqDRNXeQ"
                  ..storyGroupListHorizontalEdgePadding = 20
                  ..storylySegments = ['arabic', 'shoppable', "english"]
                  ..isProductCartEnabled = true
                  ..isProductFallbackEnabled = true
                  ..storylyLayoutDirection = "rtl"
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
                  debugPrint("storylyActionClicked -> ${story.media.actionUrl}");
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