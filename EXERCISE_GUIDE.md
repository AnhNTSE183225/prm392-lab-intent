# Intent Exercises - Implementation Guide & Testing Instructions

This document provides a detailed breakdown of each exercise implemented in the project, what was built, and step-by-step instructions to test and showcase the results.

---

## Exercise 6: Starting Activities (Explicit Intents)

### What We Implemented:
- **MainActivity** with an EditText field and "Exercise 6: Explicit Intent" button
- **ResultActivity** that receives data from MainActivity
- Data passing using `putExtra()` and `getExtras()`
- Return data mechanism using `startActivityForResult()` and `onActivityResult()`
- Toast notifications to show returned data

### Files Created/Modified:
- `MainActivity.java` - onClick() method for explicit intent
- `ResultActivity.java` - Receives and returns data
- `activity_result.xml` - Layout with TextView and EditText for return value
- `AndroidManifest.xml` - Registered ResultActivity

### How to Test & Showcase:
1. **Launch the app** - You'll see the main screen with multiple exercise buttons
2. **Enter text** in the EditText field (default: "First Activity")
3. **Click "Exercise 6: Explicit Intent"** button
4. **Verify data passing**: The ResultActivity opens and displays "Received: [your text]"
5. **Enter return data**: Type something in the "Enter return value" field
6. **Click "Return to Main"** button
7. **Verify result**: Back on MainActivity, a Toast appears showing "Returned: [your return text]"

### Key Concepts Demonstrated:
- Explicit intent creation with `new Intent(this, ResultActivity.class)`
- Data transfer using intent extras
- Sub-activity lifecycle and result handling

---

## Exercise 7: Using the Share Intent

### What We Implemented:
- **Share sending functionality** in MainActivity using `ACTION_SEND`
- **ShareActivity** that can receive share intents from other apps
- Intent filter registration for `ACTION_SEND` with `text/plain` MIME type
- Intent chooser for better user experience

### Files Created/Modified:
- `MainActivity.java` - shareData() method
- `ShareActivity.java` - Receives and displays shared text
- `activity_share.xml` - Layout to display received shared content
- `AndroidManifest.xml` - Intent filter for ACTION_SEND

### How to Test & Showcase:

#### Testing Share Sending:
1. **Enter text** in the EditText field
2. **Click "Exercise 7: Share Data"** button
3. **Verify share dialog**: Android's share chooser appears
4. **Select an app** (SMS, Email, etc.) to share the text
5. **Confirm sharing**: The selected app opens with your text pre-filled

#### Testing Share Receiving:
1. **From another app** (Notes, Browser, etc.), find text to share
2. **Trigger share action** (usually a share button or menu item)
3. **Look for "lab-2-intent"** in the share options
4. **Select our app** from the share dialog
5. **Verify reception**: ShareActivity opens displaying the received text

### Key Concepts Demonstrated:
- Implicit intents with `ACTION_SEND`
- Intent filters and MIME type handling
- Bidirectional sharing capabilities

---

## Exercise 8: Register an Activity as Browser

### What We Implemented:
- **BrowserActivity** that can handle HTTP URLs
- **Intent filter** registration for `ACTION_VIEW` with "http" scheme
- **HTML content downloading** and display in TextView
- **Network permission** and StrictMode configuration for simple networking

### Files Created/Modified:
- `BrowserActivity.java` - Custom browser implementation
- `activity_browser.xml` - ScrollView with TextView for HTML content
- `AndroidManifest.xml` - Intent filter for ACTION_VIEW + http scheme
- Added INTERNET permission

### How to Test & Showcase:

#### Method 1 - Direct Browser Test:
1. **Click "Exercise 8: Open Browser"** button
2. **Verify default browser**: System browser opens with vogella.com
3. **Note**: This demonstrates triggering a browser intent

#### Method 2 - Custom Browser Test:
1. **From another app**, click on an HTTP link
2. **Look for app chooser**: Android should show available browsers
3. **Select "Custom Browser"** (our BrowserActivity)
4. **Verify HTML display**: Raw HTML content appears in a scrollable TextView
5. **Alternative**: Use `adb shell am start -a android.intent.action.VIEW -d "http://www.vogella.com"`

### Key Concepts Demonstrated:
- Intent filter registration for system actions
- Handling URL schemes
- Network operations (simplified with StrictMode)
- Custom component registration

---

## Exercise 9: Picking an Image via an Intent

### What We Implemented:
- **Image picker functionality** using `ACTION_GET_CONTENT`
- **Modern ActivityResultLauncher** API (replaces deprecated startActivityForResult)
- **Image display** in ImageView with proper scaling
- **Error handling** for file access and image processing

### Files Created/Modified:
- `MainActivity.java` - pickImage() method and result handling
- `activity_main.xml` - Added ImageView for selected image display
- Image processing with BitmapFactory

### How to Test & Showcase:
1. **Click "Exercise 9: Pick Image"** button
2. **Verify picker dialog**: System image picker opens (Gallery, Photos, File Manager, etc.)
3. **Select an image** from your device's gallery or file system
4. **Verify image display**: 
   - Selected image appears below the buttons
   - Image is properly scaled and centered
   - Success toast message appears: "Image selected successfully!"
5. **Test error handling**: Try canceling the picker to verify graceful handling

### Key Concepts Demonstrated:
- Implicit intents with MIME type filtering
- Modern ActivityResultLauncher pattern
- Content URI handling and stream processing
- Image bitmap management and memory considerations

---

## Exercise 10: Using Different Implicit Intents

### What We Implemented:
- **ImplicitIntentsActivity** with a spinner containing 8 different intent types
- **Comprehensive intent examples** covering various Android system actions
- **Error handling** for missing apps or unsupported intents
- **Permissions** for privileged actions (phone calls, contacts, camera)

### Files Created/Modified:
- `ImplicitIntentsActivity.java` - Main logic with switch statement
- `activity_implicit_intents.xml` - Spinner and trigger button layout
- `strings.xml` - String array with intent type names
- `AndroidManifest.xml` - Added required permissions

### How to Test & Showcase:

#### Available Intent Types:
1. **Open Browser**
   - **Select from spinner** and click "Trigger Intent"
   - **Expected**: Default browser opens vogella.com
   
2. **Call Someone**
   - **Select from spinner** and click "Trigger Intent"
   - **Expected**: Direct phone call to (+49)12345789 (requires CALL_PHONE permission)
   
3. **Dial**
   - **Select from spinner** and click "Trigger Intent"
   - **Expected**: Phone dialer opens with number pre-filled
   
4. **Show Map**
   - **Select from spinner** and click "Trigger Intent"
   - **Expected**: Maps app opens showing specific coordinates (50.123,7.1434)
   
5. **Search on Map**
   - **Select from spinner** and click "Trigger Intent"
   - **Expected**: Maps app opens with search for "query"
   
6. **Take Picture**
   - **Select from spinner** and click "Trigger Intent"
   - **Expected**: Camera app opens in photo capture mode
   
7. **Show Contacts**
   - **Select from spinner** and click "Trigger Intent"
   - **Expected**: Contacts app opens showing all contacts
   
8. **Edit First Contact**
   - **Select from spinner** and click "Trigger Intent"
   - **Expected**: Contact editor opens (if contacts exist)

### Testing Instructions:
1. **Click "Exercise 10: Implicit Intents"** button
2. **Select any intent type** from the dropdown spinner
3. **Click "Trigger Intent"** button
4. **Verify behavior**: Appropriate system app opens
5. **Test error handling**: Try intents on devices without required apps
6. **Check permissions**: Some intents may request permissions on first use

### Key Concepts Demonstrated:
- Multiple implicit intent patterns
- URI scheme handling (tel:, geo:, content:)
- System integration and app interoperability
- Permission handling for privileged operations
- Graceful error handling for missing components

---

## Overall Project Features

### Modern Android Development Practices:
- **ActivityResultLauncher** instead of deprecated startActivityForResult
- **Proper permission declarations** in manifest
- **Error handling** with try-catch blocks and user feedback
- **Material Design elements** with proper layouts and styling

### Architecture Highlights:
- **Multiple activities** demonstrating different intent patterns
- **Intent filters** for system integration
- **Resource management** (proper bitmap recycling)
- **User experience** considerations (Toast feedback, error messages)

### Testing the Complete Implementation:
1. **Install the app** on an Android device or emulator
2. **Grant permissions** when prompted (phone, contacts, etc.)
3. **Test each exercise** following the individual instructions above
4. **Verify system integration** by triggering intents from other apps
5. **Check error handling** by testing on devices with missing apps

This comprehensive implementation demonstrates all the key concepts from the Intent tutorial while following modern Android development best practices.
