import React, { useState, useEffect } from "react";
import {
  SafeAreaView,
  Animated,
  StatusBar,
  Button,
  Easing,Linking
} from 'react-native';

import { Storyly } from 'storyly-react-native';

const HEIGHT = 100;
const PADDING_TOP = 10;

const useMount = func => useEffect(() => func(), []);

const useInitialURL = () => {
  const [url, setUrl] = useState(null);
  const [processing, setProcessing] = useState(true);

  useMount(() => {
    const getUrlAsync = async () => {
      // Get the deep link used to open the app
      const initialUrl = await Linking.getInitialURL();

      // The setTimeout is just for testing purpose
      setTimeout(() => {
        setUrl(initialUrl);
        setProcessing(false);
      }, 1000);
    };

    getUrlAsync();
  });

  // I am calling openStory method when app launched from deeplink! PS: Deeplink url should not be null
  if (url !== null ){
    this.storyly.openStory(url)
    console.log("uri:", url )
  }

  return { url, processing };
};

const App = () => {
  const { url: initialUrl, processing } = useInitialURL();
  console.log ("initailURL", initialUrl)

  const height = new Animated.Value(HEIGHT);
  const paddingTop = new Animated.Value(PADDING_TOP);



  const openStoryly = () => {
    Animated.timing(height, {
      toValue: HEIGHT,
      duration: 600,
      easing: Easing.bezier(0.47, 0.53, 0.37, 0.96),
    }).start();

    Animated.timing(paddingTop, {
      toValue: PADDING_TOP,
      duration: 600,
      easing: Easing.bezier(0.47, 0.53, 0.37, 0.96),
    }).start();
  }

  const closeStoryly = () => {
    Animated.timing(height, {
      toValue: 0,
      duration: 600,
      easing: Easing.bezier(0.47, 0.53, 0.37, 0.96),
    }).start();

    Animated.timing(paddingTop, {
      toValue: 0,
      duration: 600,
      easing: Easing.bezier(0.47, 0.53, 0.37, 0.96),
    }).start();
  }

  const storyGroupID = "60024"
  const storyID = "511997"

  return (
    <>
      <StatusBar barStyle="dark-content" />
      <SafeAreaView>
        <Button
          onPress={() => { 
            //this.storyly.openStory(initialUrl)
            //console.log("uri:", initialUrl )
            //this.storyly.openStoryWithId(storyGroupID, storyID);
            //console.log("Story Group ID =",storyGroupID, "- Story ID = ", storyID)
            }}
          title="Open Story"
        />
        <Animated.View
          style={{
            paddingTop: paddingTop,
            height: height,
            backgroundColor: "#fff",
          }}
        >
          <Storyly
            ref={ref => { this.storyly = ref }}
            style={{
              height: "100%",
            }}
            storyGroupSize="small"
            storylyId="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjgwMTAsImFwcF9pZCI6MTI4ODIsImluc19pZCI6MTM5NTd9.yputf40zgFjTzYsFRBpHc0dNjf4MlyiI8bthzt52JOA"
            storylySegments={["indian_users", "D30 +", "is_coinset_investor", "eng_speakers"]}
            onLoad={event => { console.log("[Storyly] onLoad", event.storyGroupList.length, event.dataSource); }}
            onFail={errorMessage => { console.log("[Storyly] onFail"); }}
            onPress={story => { console.log("[Storyly] onPress"); }}
            onEvent={eventPayload => { console.log("[Storyly] onEvent"); }}
            onStoryOpen={() => { console.log("[Storyly] onStoryOpen"); }}
            onStoryClose={() => { console.log("[Storyly] onStoryClose"); }}
            onUserInteracted={interactionEvent => { console.log("[Storyly] onStoryUserInteracted"); }}
          />
        </Animated.View>
      </SafeAreaView>
    </>
  );
};

export default App;



/*
<Button title={'Open'} onPress={openStoryly}/>
<Button title={'Close'} onPress={closeStoryly}/>
*/