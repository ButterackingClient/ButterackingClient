$organization = "ButterackingClient"
$repo = "ButterackingClient"
$get_LatestRelease = (Invoke-RestMethod -Uri "https://api.github.com/repos/$organization/$repo/releases/latest")
$zipUrl = $get_LatestRelease.assets | Where-Object { $_.name -match ".*\.zip" } | Select-Object -ExpandProperty browser_download_url
$tempExtractPath = "$env:USERPROFILE\Downloads"

Invoke-WebRequest -Uri $zipUrl -OutFile "$env:USERPROFILE\Downloads\ButterackingClient_latest.zip"

Write-Host "Extract zip file..." -ForegroundColor White

Expand-Archive -Path "$env:USERPROFILE\Downloads\ButterackingClient_latest.zip" -DestinationPath $tempExtractPath -Force

Write-Host "Moving files..." -ForegroundColor White

$extractedFolderName = (Get-ChildItem -Path $tempExtractPath | Where-Object { $_.PSIsContainer }).Name

$movePath = "$env:APPDATA\.minecraft\versions\$extractedFolderName"
Move-Item -Path "$tempExtractPath\$extractedFolderName" -Destination $movePath -Force
Remove-Item "$env:USERPROFILE\Downloads\ButterackingClient_latest.zip" -Force

Write-Host "Installation complete!" -ForegroundColor Green