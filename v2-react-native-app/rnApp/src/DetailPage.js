import { useNavigation } from '@react-navigation/native';
import React, { useState, useEffect } from 'react';
import {
  Alert, UIManager,
  StyleSheet,
  Text,
  Pressable,
  ScrollView,
  Linking,
  View,
  DeviceEventEmitter,
  SafeAreaView,
  Animated,StatusBar,Button, ActivityIndicator
} from 'react-native';import { Storyly,close } from 'storyly-react-native';


const DetailPage = ({ route }) => {
  const navigation = useNavigation()
  return (
    <SafeAreaView>
        <View
          style={{
            backgroundColor: "#ffff",
          }}
        >
          <Storyly
            ref={ref => { this.customStoryly = ref }}
            style={{
              height: "100%",
            }}
            storyGroupSize="custom"
            storyGroupIconWidth={80}
            storyGroupIconHeight={80}
            storyGroupIconCornerRadius={40}
            //storyInteractiveTextTypeface = "Montserrat-Italic"
            //storyItemTextTypeface = 'Montserrat-Italic'
            storyGroupTextSize={11}
            storyGroupListEdgePadding={25}
            storyGroupListPaddingBetweenItems={15}
            storylyId="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjcxMzcsImFwcF9pZCI6MTE3NTIsImluc19pZCI6MTI1ODl9.Z_F9S6OyES9rfwYDFV47zT0_DbP0WUwqE5EmYOpCZRQ"
            onLoad={storyGroupList => { console.log("[Storyly] onLoad"); }}
            onFail={errorMessage => { console.log("[Storyly] onFail"); }}
            /*onPress={story => { 
              console.log("[Storyly] onPress", story.id); 
              navigation.navigate('Details');
              
            }}*/
            onPress={story => { 
              console.log("[Storyly] onPress--->", story); 
              this.customStoryly.close()
              navigation.navigate("test")
            }}
            onEvent={eventPayload => { console.log("[Storyly] onEvent"); }}
            onStoryOpenFailed = {(onStoryOpenFailed) => console.log("openStoryFailed")}
            onStoryOpen={() => { console.log("[Storyly] onStoryOpen"); }}
            onStoryClose={() => { console.log("[Storyly] onStoryClose"); }}
            onUserInteracted={interactionEvent => { console.log("[Storyly] onStoryUserInteracted"); }}
          />
        </View>

        
        
      </SafeAreaView>
  );
};

export default DetailPage;