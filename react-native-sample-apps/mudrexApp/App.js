import React, { useRef, useState, useEffect } from "react";
import {
  View,
  SafeAreaView,
  Animated,
  StatusBar,
  Button,
  Easing,Linking
} from 'react-native';

import { Storyly } from 'storyly-react-native';



export default function DashboardStory() {
  const ref = useRef();


  const openDeeplink = ({ url }) => {
    setTimeout(() => {
      if (url) ref?.current?.openStory(url);
      console.log("URL--------->", url)
    }, 1000);
    
  };

  
  useEffect(() => {
    const getUrlAsync = async () => {
      const initialUrl = await Linking.getInitialURL();
      openDeeplink({ url: initialUrl });
    };

    getUrlAsync();
  }, []);

  useEffect(() => {
    Linking.addEventListener("url", openDeeplink);
    return () => {
      Linking.removeEventListener("url", openDeeplink);
    };
  }, []);

  return (
    <>
      <StatusBar barStyle="dark-content" />
      <SafeAreaView>
        <Button
          onPress={() => { 
            this.storyly.openStory("initialUrl")
            //console.log("uri:", initialUrl )
            //this.storyly.openStoryWithId(storyGroupID, storyID);
            //console.log("Story Group ID =",storyGroupID, "- Story ID = ", storyID)
            }}
          title="Open Story"
        />
        <Animated.View
          style={{
            //paddingTop: paddingTop,
            //height: height,
            backgroundColor: "#fff",
          }}
        >
          <Storyly
            ref={ref}
            style={{
              height: "100%",
            }}
            storyGroupSize="small"
            storylyId="Add your iOS_Token"
            //storylyId="Add your Android_token"
            /*storylySegments={[
              "indian_users", "D30 +", "is_coinset_investor", "eng_speakers", "italian_speakers",
              "d0 - d7"
            ]}*/
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
}